package com.mycompany.proyecto2p_grupo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    /**
     * Método principal requerido por la clase Application.
     * Se llama al iniciar la aplicación y configura la escena inicial con un tamaño de 640x480 píxeles.
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si hay problemas al cargar el archivo FXML.
     */

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Método estático utilizado para cambiar la raíz de la escena actual a otra vista definida en un archivo FXML.
     * @param fxml Nombre del archivo FXML de la nueva vista.
     * @throws IOException Si hay problemas al cargar el archivo FXML.
     */

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    /**
     * Método estático que carga y devuelve un nodo raíz (Parent) desde un archivo FXML proporcionado.
     * Utiliza un FXMLLoader para cargar el archivo FXML.
     * @param fxml Nombre del archivo FXML a cargar.
     * @return Nodo raíz (Parent) de la vista cargada desde el archivo FXML.
     * @throws IOException Si hay problemas al cargar el archivo FXML.
     */

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * Método principal que lanza la aplicación utilizando el método launch() proporcionado por la clase Application.
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        launch();
    }

}