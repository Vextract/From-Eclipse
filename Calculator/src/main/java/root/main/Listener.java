package root.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.binance.BinanceAdapter;
import root.binance.CurrenciesRate;
import root.controller.Controller;
import root.controller.ControllerImpl2;
import root.customExceptions.NotEnoughArgumentsException;
import root.customExceptions.UnsupportedOperationExceptionCustom;
import root.loggers.AbstractLogger;
import root.loggers.LogEntry;
import root.repository.RepositoryMongoImpl;
import root.repository.RepositoryMySqlImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Listener {

    private Map<Controller, List<Method>> methods = new HashMap<>();
    private Controller[] controllers;
    private AbstractLogger logger;
    private BinanceAdapter binanceAdapter;

    public Listener(Controller[] controllers, AbstractLogger logger, BinanceAdapter binanceAdapter) {
        this.controllers = controllers;
        this.logger = logger;
        this.binanceAdapter = binanceAdapter;
        for (Controller controller: controllers) {
            methods.put(controller, List.of(controller.getClass().getDeclaredMethods()));
        }
        run();
    }

    public void run(){
        info();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String str = reader.readLine();
                if (str.equalsIgnoreCase("info")) {
                    info();
                    continue;
                }

                if (str.equalsIgnoreCase("binance")) {
                    System.out.println("С помощью команды \"rates\" вы можете увидеть доступные пары");
                    System.out.println("Для получения определенной пары напишите \"conversion код_пары\"");
                    String str2 = reader.readLine();
                    String[] parts = str2.split(" ");
                    if (parts[0].equalsIgnoreCase("rates")) {
                        List<CurrenciesRate> list = binanceAdapter.getAllConversionPairs();

                        System.out.println("Available options: ");

                        for (CurrenciesRate one: list) {
                            System.out.println(one.toString());
                        }
                        continue;
                    } else if (parts[0].equalsIgnoreCase("conversion")) {
                        CurrenciesRate obj = binanceAdapter.getRate(parts[1]);
                        System.out.println(obj.toString());
                        continue;
                    }
                }

                if (str.equalsIgnoreCase("end")) {
                    break;
                }

                String[] inputData = str.split(" ", 2);
                String[] classAndMethod = inputData[0].split("/");

                try {
                    for (Controller controller: methods.keySet()) {
                        if (controller.getClass().getSimpleName().equals(classAndMethod[0])) {
                            for (Method method:methods.get(controller)) {
                                if (method.getName().equals(classAndMethod[1])) {
                                    try {
                                        method.setAccessible(true);
                                        for (Object obj:controllers) {
                                            if (obj.getClass().getSimpleName()
                                                    .equals(controller.getClass().getSimpleName())) {

                                                String[] args = inputData[1].trim().split(" ");

                                                Response response = (Response) method.invoke(obj, (Object) args);
                                                handleResponse(response);

                                            }
                                        }
                                    } catch (IllegalAccessException | InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.error(new LogEntry(new NumberFormatException()));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info() {
        System.out.println("Список доступных команд и методов:\n");

        System.out.println("\t\"info\" - для получения списка команд и методов");
        System.out.println("\t\"log\" - для получения логов");
        System.out.println("\t\"binance\" - для доступа к сервису Binance");
        System.out.println("\t\"Название_контроллера/название_метода аргументы\" " +
                "- для исполнения методов контроллеров\n");
        for (Map.Entry<Controller, List<Method>> entry: methods.entrySet()) {
            System.out.println("Методы " + entry.getKey().getClass().getSimpleName() + ":");
            for (Method method: entry.getValue()) {
                System.out.println("\t" + method.getName() + Arrays.toString(method.getParameters()));
            }
            System.out.println();
        }
    }

    public void handleResponse(Response response) {
        if (response.getException() == null) {
            System.out.println("Ответ: " + response.getResult());
        } else{
            if (response.getException() instanceof NotEnoughArgumentsException) {
                System.out.println("Недостаточно аргументов");
            } else if (response.getException() instanceof NumberFormatException) {
                System.out.println("Неправильный формат аргументов");
            } else if (response.getException() instanceof UnsupportedOperationExceptionCustom) {
                System.out.println("Операция " +
                        ((UnsupportedOperationExceptionCustom) response.getException()).getOperation() +
                        " не поддерживается");
            }
        }
    }
}
