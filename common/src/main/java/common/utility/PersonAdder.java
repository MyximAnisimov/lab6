package common.utility;

import common.collections.Coordinates;
import common.collections.Country;
import common.collections.Location;
import common.collections.Person;
import common.exceptions.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для создания элемента коллекции (Человека) через ввод пользователя
 */
public class PersonAdder {
    Scanner sc;
    private boolean scriptMode;
    /**
     * Функция для возврата объекта класса Scanner. Предназначена для считывания ввода пользователя
     * @return
     */
    public Scanner getUserScanner() {
        return sc;
    }

    /**
     * Задаёт значение для объекта класса Scanner
     * @param sc объект класса Scanner, который используется для считывания введённых пользователем данных
     */
    public void setUserScanner(Scanner sc) {
        this.sc = sc;
    }

    public PersonAdder(Scanner sc){
        this.sc=sc;
        scriptMode=false;
    }

    /**
     * Устанавливает значение true для исполнения скрипта. Начало работы скрипта
     */
    public void setScriptMode(){
        scriptMode = true;
    }

    /**
     * Устанавливает значение false для исполнения скрипта. Конец работы скрипта
     */
    public void setUserMode(){
        scriptMode = false;
    }


    /**
     * Задаёт имя для нового элемента коллекции
     * @param sc введённое пользователем имя элемента
     * @return имя нового элемента
     * @throws IncorrectInputInScriptException
     */
    public  String setName(Scanner sc) throws IncorrectInputInScriptException {
        String name;
        while (true) {
            CustomConsole.print("Введите имя человека: ");
            try {
                name = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                CustomConsole.printError("имя не должно быть пустым!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                CustomConsole.printError("имя не может быть загружено");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    CustomConsole.printError("Выполнен выход из программы!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                CustomConsole.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     *  Задаёт координаты нового элемента коллекции
     * @param sc введённые пользователем координаты
     * @return элемент класса Coordinates с координатами
     * @throws IncorrectInputInScriptException
     * @throws OutOfLimitsException
     */
    public Coordinates setCoordinates(Scanner sc) throws IncorrectInputInScriptException, OutOfLimitsException {
        sc=new Scanner(System.in);
        double x;
        while (true) {
            try {
                CustomConsole.print("Введите координату х: ");
                String s = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(s);
                if (s.equals("")) throw new MustBeNotEmptyException();
                x = Double.parseDouble(s);
                break;
            } catch (NumberFormatException e) {
                CustomConsole.printError("Координата X должна быть формата double");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                CustomConsole.printError("Непредвиденная ошибка!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                CustomConsole.printError("Координата X не может быть пуста!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        }
        int y;
        while (true) {
            try {
                CustomConsole.print("Введите координату y: ");
                String s = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(s);
                if (s.equals("")) throw new MustBeNotEmptyException();

                y =Integer.parseInt(s);
                if(y<-438) throw new OutOfLimitsException();
                break;
            } catch (NoSuchElementException e) {
                CustomConsole.printError("Координата Y не может быть загружена");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    CustomConsole.printError("Выполнен выход при помощи команды Ctrl+D!");
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                CustomConsole.printError("Координата Y должна быть формата int");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                CustomConsole.printError("Непредвиденная ошибка!");
                System.exit(0);
            } catch (MustBeNotEmptyException e) {
                CustomConsole.printError("Координата Y не может быть пуста!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
            catch(OutOfLimitsException e){
                CustomConsole.printError("Координата Y меньше -438!");
            }

        }
        Coordinates coord=new Coordinates(x,y);
        return coord;
    }

    /**
     * Выводит дату создания коллекции
     * @return дата создания коллекции
     */
    public LocalDate setCreationDate(){
        while (true) {
            try {
                return LocalDate.now();
            } catch (DateTimeException e) {
                CustomConsole.printError("Возникла ошибка с датой создания");
            }
        }
    }

    /**
     * Задаёт рост нового элемента коллекции
     * @param sc введённое пользователем значение роста
     * @return рост элемента коллекции (Человека)
     * @throws IncorrectInputInScriptException
     */
    public Integer setHeight(Scanner sc)throws IncorrectInputInScriptException{
        Integer height;
        while(true){
        try{
            CustomConsole.print("Введите рост человека: ");
            String s = sc.nextLine().trim();
            if(scriptMode) CustomConsole.printLn(s);
            if (s.equals("")) throw new MustBeNotEmptyException();

            height =Integer.parseInt(s);
            if(height<=0) throw new OutOfLimitsException();
            break;}
        catch(MustBeNotEmptyException e){
            CustomConsole.printError("имя не должно быть пустым!");
            if (scriptMode) throw new IncorrectInputInScriptException();
        }
        catch (NumberFormatException e) {
            CustomConsole.printError("Рост человек должен быть в формате Integer");
            if (scriptMode) throw new IncorrectInputInScriptException();
        }
        catch(OutOfLimitsException e){
            CustomConsole.printError("Поле height должно быть больше нуля!");
        }}
        return height;
    }

    /**
     * Задаёт значение даты дня рождения человека
     * @param sc введённые пользователем данные
     * @return элемент класса LocalDate с датой дня рождения
     * @throws IncorrectInputInScriptException
     * @throws WrongDateFormatException
     */
    public LocalDate setBirthdayDate(Scanner sc) throws IncorrectInputInScriptException,WrongDateFormatException {
        sc=new Scanner(System.in);
        LocalDate localdate;
        int year;
        while(true){
            try{
            CustomConsole.print("Введите год рождения человека: ");
            String s = sc.nextLine().trim();
            if(scriptMode) CustomConsole.printLn(s);
            if (s.equals("")){
                year=0;
            }
            else{
            year = Integer.parseInt(s);
            if(year<0||year>LocalDate.now().getYear()) throw new WrongDateFormatException();}
            break;}
            catch (NumberFormatException e) {
                CustomConsole.printError("Год рождения должен быть в формате int");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        catch(WrongDateFormatException e){
            CustomConsole.printError("Некорректный ввод года рождения!");
            if(scriptMode) throw new IncorrectInputInScriptException();}
        }
        int month;
        while(true){
            try{
                CustomConsole.print("Введите месяц рождения человека: ");
                String s = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(s);
                if (s.equals("")){
                    month=0;
                }
                else{
                month = Integer.parseInt(s);
                if(month<0||month>12) throw new WrongDateFormatException();}
                break;}
            catch (NumberFormatException e) {
                CustomConsole.printError("Месяц рождения должен быть в формате int!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
        catch(WrongDateFormatException e){
            CustomConsole.printError("Некорректный ввод месяца рождения!");
            if(scriptMode) throw new WrongDateFormatException();}
        }
        int day;
        while(true){
        try{
            CustomConsole.print("Введите день рождения человека: ");
            String s = sc.nextLine().trim();
            if(scriptMode) CustomConsole.printLn(s);
            if (s.equals("")){
                day=0;
            }else{
            day = Integer.parseInt(s);
                if(day<0||day>31) throw new WrongDateFormatException();
            if(month==2&&day>29) throw new WrongDateFormatException();}
            break;}
        catch (NumberFormatException e) {
            CustomConsole.printError("День рождения должен быть в формате int");
            if (scriptMode) throw new IncorrectInputInScriptException();
        }
        catch(WrongDateFormatException e){
        CustomConsole.printError("Некорректный ввод дня рождения!");
        if(scriptMode) throw new IncorrectInputInScriptException();}
        }
        if(day==0&&year==0&&month==0){
           localdate=null;
        }
        else{
        localdate=LocalDate.of(year,month,day);
        }
        return localdate;
    }

    /**
     * Задаёт значение id паспорта
     * @param sc введённые пользоавтелем данные
     * @return id паспорта
     * @throws IncorrectInputInScriptException
     */
    public String setPassportID(Scanner sc)throws IncorrectInputInScriptException{
        sc=new Scanner(System.in);
        String passportID;
        while (true) {
            CustomConsole.print("Введите номер паспорта: ");
            try {
                passportID = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(passportID);
                if (passportID.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                CustomConsole.printError("ID паспорта не должно быть пустым!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (NoSuchElementException e) {
                CustomConsole.printError("ID паспорта не может быть загружено");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    CustomConsole.printError("Выполнен выход из программы!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                CustomConsole.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return passportID;
    }

    /**
     * Задаёт националньность человека
     * @param sc введённые пользователем данные
     * @return страна, в которой родился человек
     * @throws IncorrectInputInScriptException
     * @throws UnknownCountryException
     */
    public Country setNationality(Scanner sc) throws IncorrectInputInScriptException,UnknownCountryException{
        sc=new Scanner(System.in);
        Country nationality;
        while(true){
        try{

        CustomConsole.printLn("Укажите национальность человека, представленную в списке: ");
        CustomConsole.printLn(Country.INDIA);
        CustomConsole.printLn(Country.ITALY);
        CustomConsole.printLn(Country.JAPAN);
        CustomConsole.printLn(Country.VATICAN);
        CustomConsole.printLn(Country.UNITED_KINGDOM);
            String country=sc.nextLine();
        if(country.equals("JAPAN")){
            nationality = Country.JAPAN;
            return nationality;
        }
        else if(country.equals("INDIA")){
            nationality=Country.INDIA;
            return nationality;
        }
        else if(country.equals("ITALY")){
            nationality= Country.ITALY;
            return nationality;
        }
        else if(country.equals("VATICAN")){
            nationality= Country.VATICAN;
            return nationality;
        }
        else if(country.equals("UNITED KINGDOM")){
            nationality= Country.UNITED_KINGDOM;
            return nationality;
        }
        else throw new UnknownCountryException();}
        catch(UnknownCountryException e){
            CustomConsole.printError("Национальность человека не распознана. Повторите попытку");
            if(scriptMode) throw new IncorrectInputInScriptException();
        }}
    }

    /**
     * Задаёт значение координат и название места, в котором находится человек
     * @param sc введённые пользователем данные
     * @return элемент класса Location, в котором указаны координаты и название места
     * @throws IncorrectInputInScriptException
     */
    public Location setLocation(Scanner sc) throws IncorrectInputInScriptException{
        sc=new Scanner(System.in);
        Long x;
        while(true){
            try{
                CustomConsole.print("Введите координату Х локации: ");
                String s = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(s);
                if (s.equals(""))throw new MustBeNotEmptyException();
                x = Long.parseLong(s);
                break;}
            catch (MustBeNotEmptyException e) {
                CustomConsole.printError("Координата Х не может быть пустой!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }catch (NoSuchElementException e) {
                CustomConsole.printError("Координата Х не может быть загружена!");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    CustomConsole.printError("Выполнен выход из программы!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                CustomConsole.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        int y;
        while(true){
            try{
                CustomConsole.print("Введите координату Y локации: ");
                String s = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(s);
                if (s.equals("")){
                    y=0;
                }
                else{
                    y = Integer.parseInt(s);}
                break;}
            catch (NoSuchElementException e) {
                CustomConsole.printError("Координата Y не может быть загружена!");
                if (scriptMode) throw new IncorrectInputInScriptException();
                if(!sc.hasNext()) {
                    CustomConsole.printError("Выполнен выход из программы!");
                    System.exit(0);
                }
            } catch (IllegalStateException e) {
                CustomConsole.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        Float z;
        while(true){
            try{
                CustomConsole.print("Введите координату Z локации: ");
                String s = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(s);
                if (s.equals("")) throw new MustBeNotEmptyException();
                    z = Float.parseFloat(s);
                break;}
            catch (NumberFormatException e) {
                CustomConsole.printError("Координата Z должна быть в формате Float");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }
            catch (MustBeNotEmptyException e) {
                CustomConsole.printError("Координата Z не может быть пустой!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            }

        }
        String name;
        while(true){
        CustomConsole.print("Введите название локации: ");
        try {
            name = sc.nextLine().trim();
            if(scriptMode) CustomConsole.printLn(name);
            if (name.equals("")){
                name="null";
            }
            break;
        }
        catch (NoSuchElementException e) {
            CustomConsole.printError("название локации не может быть загружено");
            if (scriptMode) throw new IncorrectInputInScriptException();
            if(!sc.hasNext()) {
                CustomConsole.printError("Выполнен выход из программы!");
                System.exit(0);
            }
        } catch (IllegalStateException e) {
            CustomConsole.printError("Непредвиденная ошибка!");
            System.exit(0);
        }
    }
         Location location = new Location(x,y,z,name);
        return location;
    }

    /**
     * Возвращает готовый элемент коллекции
     * @return элемент коллекции
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException{
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try{
                CustomConsole.printLn(finalQuestion);
                answer = sc.nextLine().trim();
                if(scriptMode) CustomConsole.printLn(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new OutOfLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                CustomConsole.printError("The response was not recognized!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (OutOfLimitsException exception) {
                CustomConsole.printError("The answer must be represented by the signs '+' or '-'!");
                if (scriptMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                CustomConsole.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }
    public Person build()throws IncorrectInputInScriptException, OutOfLimitsException, WrongDateFormatException, UnknownCountryException {
        return new Person(1, setName(sc),setCoordinates(sc),setCreationDate(),setHeight(sc),setBirthdayDate(sc),setPassportID(sc),setNationality(sc),setLocation(sc));
    }
}
