package firok.spring.vuefx;

public class VueFxTestFxApplication extends VueFxApplicationFx
{

	public VueFxTestFxApplication()
	{
		super(
				VueFxTestSpringApplication.class,
				VueFxTestController.class,
				"classpath:/firok/spring/vuefx/test.html"
		);
	}
}
