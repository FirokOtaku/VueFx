package firok.spring.vuefx;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public abstract class VueFxController
{
	Stage stage;

	@FXML
	WebView webview;

	JSObject window;

	public final Object get(String key)
	{
		return window.getMember(key);
	}
	public final void set(String key, Object value)
	{
		window.setMember(key, value);
	}
	public final void remove(String key)
	{
		window.removeMember(key);
	}

	/**
	 * 子类实现这个方法, 对各种东西进行一定的调整
	 * */
	protected abstract void postInit();
}
