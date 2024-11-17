package pt.iscte.poo.tools;

public class Logger {
    public enum MessageType {
        NO_FORMAT,
        ALERT,
        INFO,
        ERROR,
    }

    private String[] msgColors;

    private static Logger onlyLogger;

    private Logger() {
        msgColors = new String[4];
        this.msgColors[0] = "\u001B[0m"; // Cor padrão
        this.msgColors[1] = "\033[0;33m"; // Para alertas
        this.msgColors[2] = "\033[0;32m"; // Para informações gerais
        this.msgColors[3] = "\u001B[31m"; // Para erros
    }

    public static Logger getLogger() {
        if(onlyLogger == null)
            onlyLogger = new Logger();

        return onlyLogger;
    }

    public void log(String msg, MessageType type) {
        if(type == null) type = MessageType.INFO;

        System.out.println(this.msgColors[type.ordinal()] + msg + this.msgColors[MessageType.NO_FORMAT.ordinal()]);
    }
}
