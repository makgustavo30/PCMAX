package modelo;import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.*;



public class logger1 {

    Date fecha = new Date();
    Logger log;

    public logger1(String workspace) throws IOException {

        log = Logger.getLogger(logger1.class);

        // Formato de la hora
        SimpleDateFormat formato = new SimpleDateFormat("dd.MM.yyyy");
        String fechaAc = formato.format(fecha);

        // Patrón que seguirá las lineas del log
        PatternLayout defaultLayout = new PatternLayout("%p: %d{HH:mm:ss} --> %m%n");
        RollingFileAppender rollingFileAppender = new RollingFileAppender();

        //Definimos el archivo dónde irá el log (la ruta)
        rollingFileAppender.setFile(workspace + "logs/archivo_" + fechaAc + ".log", true, false, 0);
        rollingFileAppender.setLayout(defaultLayout);

        log.removeAllAppenders();
        log.addAppender(rollingFileAppender);
        log.setAdditivity(false);
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }
}
