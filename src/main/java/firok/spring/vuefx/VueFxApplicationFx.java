package firok.spring.vuefx;

import firok.topaz.Version;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @implNote 这个类需要工作在 Spring Boot 环境下
 * */
public abstract class VueFxApplicationFx<TypeController extends VueFxController> extends Application
{
	public static final Version VERSION = new Version(0, 2, 0);

	static // 加载 classpath URL 协议
	{
		TomcatURLStreamHandlerFactory.getInstance();
	}

	protected final Class<?> classSpringApplication;
	protected final Class<TypeController> classController;
	protected final String url;
	protected VueFxApplicationFx(
			Class<?> classSpringApplication,
			Class<TypeController> classController,
			String url
	)
	{
		this.classSpringApplication = classSpringApplication;
		this.classController = classController;
		this.url = url;
	}

	/**
	 * 相关的 Spring 容器
	 * */
	protected ConfigurableApplicationContext context;

	@Override
	public void init() throws Exception
	{
		context = new SpringApplicationBuilder(classSpringApplication).run();
	}

	protected TypeController controller;

	@Override
	public void start(Stage stage) throws Exception
	{
		var fxSource = new ClassPathResource("/firok/spring/vuefx/vuefx.fxml");
		var fxLoader = new FXMLLoader();
		fxLoader.setLocation(fxSource.getURL());
		fxLoader.setControllerFactory(c -> {
			try { return classController.getConstructor().newInstance(); }
			catch (Exception any) { throw new RuntimeException(any); }
		});
		var sceneContent = fxLoader.<Parent>load();
		var scene = new Scene(sceneContent);
		this.controller = fxLoader.getController();

		controller.stage = stage;
		stage.setScene(scene);
		var engine = this.controller.webview.getEngine();
		engine.setOnError(error -> System.err.println("error: " + error));
		engine.setOnAlert(alert -> System.err.println("alert: " + alert));
		engine.load(url);
		engine.getLoadWorker().stateProperty().addListener(state -> {
			controller.window = (JSObject) engine.executeScript("window");
			controller.set("VueFxApplication", this);
			controller.set("VueFxController", controller);
			engine.executeScript("typeof VueFxPostInit === 'function' ? VueFxPostInit() : undefined");
			controller.postInit();
			stage.show();
		});
	}

	@Override
	public void stop()
	{
		context.stop();
		Platform.exit();
	}
}
