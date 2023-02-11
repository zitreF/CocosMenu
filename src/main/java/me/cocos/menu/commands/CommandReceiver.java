package me.cocos.menu.commands;


import me.cocos.menu.holders.MenuHolder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CommandReceiver extends Command {

    private final MenuHolder holder;
    private final me.cocos.menu.commands.Command command;


    public CommandReceiver(me.cocos.menu.commands.Command command, MenuHolder holder) {
        super(command.name());
        this.command = command;
        this.holder = holder;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            this.holder.run((Player) sender, command);
            return true;
        }
        sender.sendMessage("Musisz byc graczem aby uzyc tej komendy!");
        return false;
    }

}