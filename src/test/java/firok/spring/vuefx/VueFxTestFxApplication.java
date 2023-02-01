package firok.spring.vuefx;

public class VueFxTestFxApplication extends VueFxApplicationFx<VueFxTestController>
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
