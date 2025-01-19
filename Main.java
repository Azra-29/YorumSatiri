//tüm kodların çalışması için main class gerekli bu da main class işte, 8. satırda robotu main seçtik 
package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
public final class Main {
  private Main() {}
  public static void main(String... args) {
    RobotBase.startRobot(Robot::new); //roboru tanımladık ve main olarak seçtik 
  }
}
