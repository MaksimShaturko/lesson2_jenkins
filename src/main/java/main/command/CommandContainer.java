package main.command;

import main.command.impl.AddUserCommand;
import main.command.impl.DeleteUserCommand;
import main.command.impl.EditUserCommand;
import main.command.impl.ErrorCommand;
import main.command.impl.GetAllUsersCommand;
import main.command.impl.GetUserCommand;
import main.command.impl.GoToAddUserPage;
import main.command.impl.GoToEditUserPage;
import main.command.impl.GoToUsersPage;

public enum CommandContainer {
    GET_ALL_USERS(GetAllUsersCommand.getInstance()),
    ERROR_COMMAND(ErrorCommand.getInstance()),
    GO_TO_USERS_PAGE(GoToUsersPage.getInstance()),
    DELETE_USER(DeleteUserCommand.getInstance()),
    GO_TO_ADD_USER_PAGE(GoToAddUserPage.getInstance()),
    ADD_USER(AddUserCommand.getInstance()),
    GO_TO_EDIT_USER_PAGE(GoToEditUserPage.getInstance()),
    GET_USER(GetUserCommand.getInstance()),
    EDIT_USER(EditUserCommand.getInstance());

    private final Command command;

    CommandContainer(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }





}
