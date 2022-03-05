package com.fleet.javafx;

import com.fleet.javafx.config.DemoSplashScreen;
import com.fleet.javafx.view.DemoView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavafxApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(JavafxApplication.class, DemoView.class, new DemoSplashScreen(), args);
    }

    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        stage.setTitle("标题");
        stage.setWidth(300);
        stage.setHeight(300);
    }
}
