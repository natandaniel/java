package structural.facade;

public class Client {
  public static void main(String[] args) {
    Television television = new Television();
    AudioSystem audioSystem = new AudioSystem();
    BluRayPlayer bluRayPlayer = new BluRayPlayer();

    HomeTheaterFacade homeTheater = new HomeTheaterFacade(television, audioSystem, bluRayPlayer);

    homeTheater.startMovie("Facade Movie");
    System.out.println();

    homeTheater.stopMovie();
  }

}
