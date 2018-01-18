package by.skoriyVladislav;

import by.skoriyVladislav.service.command.command_type.TypeCommand;

public class main {
    public static void main(String... agr) {
        System.out.println(TypeCommand.valueOf("index".toUpperCase()));
    }
}
