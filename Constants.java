//bu sınıfı genel bir düzenliyici gibi düşünebiliriz sanırım. Her zaman atamalar public te olmalıdır çünkü aslında temelde kullanılacak bütün parçaların pim
//atamaları vs. burda. Burda somut olarak çalışan bi şey yok ama kodlama olarak hangi parça hangi pime takılı onun atamaları var. Öyle yani.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants { //Constants diye bi sınıf oluşturduk

  public static final class ControllersConstants { //ve konsolları ekledik
    public static final int FIRST_DRIVERS_CONTROLLER = 0; // controller port for egemen's gamepad   // Egemenin konsolunu ve hangi pime bağlı olduğunu tanımladık
    public static final int SECOND_DRIVERS_CONTROLLER = 1;  // controller port for kazım's gamepad  //Kazımın konsolunu ve hangi pime bağlı olduğunu tanımladık
  }

  
  public static final class LaunchersConstants {
      public static final int LEFT_CIM_MOTOR = 7; //sol CIM motoru yine aynı şekiilde pimini ve kendisini tanımladık
      public static final int RIGHT_CIM_MOTOR = 4;  //aynısını sağ CIM için yaptık
  }

  public static final int LF_MOTOR_CHANNEL = 5; //Sol ön motoru ve pimini tanımladık
  public static final int RF_MOTOR_CHANNEL = 8; //Sağ ön motoru ve pinini tanımladık
  public static final int LR_MOTOR_CHANNEL = 14; //sol arka ..
  public static final int RR_MOTOR_CHANNEL = 1; //sağ arka..

  public static final WPI_VictorSPX LF_MOTOR = new WPI_VictorSPX(LF_MOTOR_CHANNEL); //motorlar motor denetliyicileriyle birlikte tanımlanır 
  public static final WPI_VictorSPX RF_MOTOR = new WPI_VictorSPX(RF_MOTOR_CHANNEL); //aynısı sol ön sol arka sağ ön ve sağ arka için yapılır
  public static final WPI_VictorSPX LR_MOTOR = new WPI_VictorSPX(LR_MOTOR_CHANNEL); //...
  public static final WPI_VictorSPX RR_MOTOR = new WPI_VictorSPX(RR_MOTOR_CHANNEL); //...

  
  public static final DifferentialDrive FRONT_DRIVE = new DifferentialDrive(LF_MOTOR, RF_MOTOR); //ve en son ön sürücüler oluşturulur
  public static final DifferentialDrive REAR_DRIVE = new DifferentialDrive(LR_MOTOR, RR_MOTOR); //ve aynı şekilde arka sürücüler..
}
