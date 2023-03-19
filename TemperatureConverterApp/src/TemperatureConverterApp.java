import java.util.Scanner;

interface Temperature {
    double toCelsius(double temperature);
    double toFahrenheit(double temperature);
}

class TemperatureConverter implements Temperature {
    public double toCelsius(double temperature) {
        return (temperature - 32.0) * 5.0 / 9.0;
    }
    
    public double toFahrenheit(double temperature) {
        return temperature * 9.0 / 5.0 + 32.0;
    }
}

interface UserInput {
    double getTemperature();
    String getUnit();
}

class ConsoleInput implements UserInput {
    Scanner scanner = new Scanner(System.in);
    
    public double getTemperature() {
        System.out.print("Enter the temperature: ");
        return scanner.nextDouble();
    }
    
    public String getUnit() {
        System.out.print("Enter the unit (C/F/K): ");
        return scanner.next();
    }
}

class KelvinConverter implements Temperature {
    public double toCelsius(double temperature) {
        return temperature - 273.15;
    }
    
    public double toFahrenheit(double temperature) {
        return temperature * 9.0 / 5.0 - 459.67;
    }
}

class RankineConverter implements Temperature {
    public double toCelsius(double temperature) {
        return (temperature - 491.67) * 5.0 / 9.0;
    }
    
    public double toFahrenheit(double temperature) {
        return temperature - 459.67;
    }
}

class ReaumurConverter implements Temperature {
    public double toCelsius(double temperature) {
        return temperature * 5.0 / 4.0;
    }
    
    public double toFahrenheit(double temperature) {
        return temperature * 9.0 / 4.0 + 32.0;
    }
}

class DelisleConverter implements Temperature {
    public double toCelsius(double temperature) {
        return 100.0 - temperature * 2.0 / 3.0;
    }
    
    public double toFahrenheit(double temperature) {
        return temperature * 9.0 / 2.0 + 32.0;
    }
}

public class TemperatureConverterApp {
    public static void main(String[] args) {
        TemperatureConverter celsiusConverter = new TemperatureConverter();
        TemperatureConverter fahrenheitConverter = new TemperatureConverter();
        KelvinConverter kelvinConverter = new KelvinConverter();
        RankineConverter rankineConverter = new RankineConverter();
        ReaumurConverter reaumurConverter = new ReaumurConverter();
        DelisleConverter delisleConverter = new DelisleConverter();
        UserInput userInput = new ConsoleInput();
        
        double temperature = userInput.getTemperature();
        String unit = userInput.getUnit();
        
        switch (unit.toUpperCase()) {
            case "C":
                double fahrenheit = fahrenheitConverter.toFahrenheit(temperature);
                double kelvin = kelvinConverter.toFahrenheit(temperature);
                double rankine = rankineConverter.toFahrenheit(temperature);
                double reaumur = reaumurConverter.toCelsius(temperature);
                double delisle = delisleConverter.toCelsius(temperature);
                System.out.printf("%.1f°C = %.1f°F = %.1fK = %.1f°R = %.1f°Ré = %.1f°De", temperature, fahrenheit, kelvin, rankine, reaumur, delisle);
                break;
            case "F":
                double celsius = celsiusConverter.toCelsius(temperature);
                kelvin = kelvinConverter.toCelsius(temperature);
                rankine = rankineConverter.toFahrenheit(temperature);
                reaumur = reaumurConverter.toCelsius(celsius);
                delisle = delisleConverter.toCelsius(celsius);
                System.out.printf("%.1f°F = %.1f°C = %.1fK = %.1f°R = %.1f°Ré = %.1f°De", temperature, celsius, kelvin, rankine, reaumur, delisle);
                break;
            case "K":
                celsius = kelvinConverter.toCelsius(temperature);
                fahrenheit = kelvinConverter.toFahrenheit(temperature);
                rankine = temperature * 1.8;
                reaumur = celsius * 0.8;
                delisle = delisleConverter.toCelsius(kelvinConverter.toCelsius(temperature));
                System.out.printf("%.1fK = %.1f°C = %.1f°F = %.1f°R = %.1f°Ré = %.1f°De", temperature, celsius, fahrenheit, rankine, reaumur, delisle);
                break;
            default:
                System.out.println("Invalid unit. Please enter C, F, or K.");
        }
    }
}

