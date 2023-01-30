package firok.spring.vuefx;

public class VueFxTestController extends VueFxController
{
	public void log(Object obj)
	{
		System.out.println("log: " + obj);
	}

	@Override
	protected void postInit()
	{
		stage.setTitle("VueFx Demo in " + VueFxApplicationFx.VERSION);
		stage.setWidth(400);
		stage.setHeight(250);
	}
}
