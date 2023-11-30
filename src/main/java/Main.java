import menus.MainMenu;

// TODO:
//  1. Сделать статические методы для исполььзования с method reference :: Можно передавать и нестатики
//  через имяОбъекта::ссылкаНаМетод
//  2. Попробовать сделать один FileWriter и передавать его везде.
//  3. Можно использовать лямбда-выражения на дублирующих readFromConsole() и readFromConsoleAndSaveToFile()
public class Main {
    public static void main(String[] args) {
        new MainMenu().mainMenu();
    }
}
