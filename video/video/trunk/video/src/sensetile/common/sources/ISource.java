package sensetile.common.sources;

/**
 *
 * @author SenseTile
 */
public interface ISource
{
  public static final int DEFAULT_WIDTH = 320;
  public static final int DEFAULT_HEIGHT = 240;
  public static final ISource NO_SOURCE = null;

  String getDeviceName();
  String getUUID();

  int getWidth();
  void setWidth(final int width);

  int getHeight();
  void setHeight(final int height);

  boolean isPaused();
  boolean isPlaying();
  boolean isSelected();

  String getLocation();
  void setSelected(final boolean selected);
  void stopSource();
  void startSource();
  void pauseSource();
  public void play();
  boolean isEqual(final ISource targetSource);
  boolean hasPathAs(final String path);
  
}
