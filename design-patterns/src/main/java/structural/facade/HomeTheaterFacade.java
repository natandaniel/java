package structural.facade;

public class HomeTheaterFacade {
  private Television television;
  private AudioSystem audioSystem;
  private BluRayPlayer bluRayPlayer;

  public HomeTheaterFacade(Television television, AudioSystem audioSystem,
      BluRayPlayer bluRayPlayer) {
    this.television = television;
    this.audioSystem = audioSystem;
    this.bluRayPlayer = bluRayPlayer;
  }

  public void startMovie(String movie) {
    System.out.println("Starting movie...");
    television.turnOn();
    audioSystem.turnOn();
    audioSystem.setVolume(20);
    bluRayPlayer.turnOn();
    bluRayPlayer.playMovie(movie);
    System.out.println("Enjoy movie!");
  }

  public void stopMovie() {
    System.out.println("Stopping movie...");
    bluRayPlayer.turnOff();
    audioSystem.turnOff();
    television.turnOff();
    System.out.println("Home cinema off.");
  }
}
