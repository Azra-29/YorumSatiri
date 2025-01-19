//yine gerekli paketleri vs çağırıyoruz

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShootingSubsystem extends SubsystemBase { //Atış sistemi diye sınıf oluşturduk
  public final PWMSparkMax leftCIMMotor; //motorları tanımlıyozz (sol)
  public final PWMSparkMax rightCIMMotor; //motorları tanımlıyozz (sağ)
  public final RelativeEncoder encoder; //encoder tanımlıyoruz
  public final PWMSparkMax intakeNEO = new PWMSparkMax(3); //intake motorunu tanımlıyoruz (3 nolu pine takılmış)
  private final CANSparkMax liftIntakeNEO; //lift motorunu tanımlıyoz

  public ShootingSubsystem() { //atış sistemi içinde:
    liftIntakeNEO = new CANSparkMax(26, CANSparkLowLevel.MotorType.kBrushless); //lift motorunu tanımlıyoruz
    leftCIMMotor = new PWMSparkMax(Constants.LaunchersConstants.LEFT_CIM_MOTOR);  // CIM motor left. //sol cım motorunu sparkmax ile birleştiriyoruz sanırım
    rightCIMMotor = new PWMSparkMax(Constants.LaunchersConstants.RIGHT_CIM_MOTOR); // CIM motor right. //sağ CIM motorunu da aynı şekilde spark la eşleştiriyoruz veya onun gibi bir şey uygun kelimeyi bulamadım

    encoder = liftIntakeNEO.getEncoder(); //lift motoruna encoder tanımlıyoruz

    encoder.setPosition(0); //encoder aracılığıyla pozisyonu sıfırlıyoruz
    encoder.setPositionConversionFactor(0.035); //ve 0.35lik bir pozisyon dönüşüm faktörü ayarlıyoruz

  }

  public void autoPos(double desiredPos){ //istenilen pozisyon ve mevcut pozisyon diye iki değişken alıyoruz
    double getPos = encoder.getPosition(); //mevcut pozisyonu alıyoruz
    double speed = (getPos - desiredPos) * 0.7; //ve mevcut pozisyondan istenilen pozisyona gitmek için hızı hesaplıyoruz

    liftIntakeNEO.set(-speed); //ve lift motoruna hesaplanan hızı veriyoruz
  }

  public void encoderReadout(double yAxis) { //TestPeriodic Function 
    double pos = encoder.getPosition(); //encoderdan pozisyonu alıyoruz
    double rpm = encoder.getPositionConversionFactor(); //encoderdan rpm yi (devir) alıyoruz

    System.out.println("RPM = " + rpm); //rpm yi yazdırıyoruz
    System.out.println("Pos = " + pos); //pozisyonu yazdırıyoruz

    liftIntakeNEO.set(yAxis * 0.3); //lift motoruna hız ayarlıyoruz
  }

  public void getPOVValues(int powValue){ //güç değerine göre yapılacak işlemleri ayarlıyoruz
    switch (powValue) {
      // Unpressed
      case -1 -> { //-1 olması durumunda
        intakeNEO.set(0.0); //intake motorunu durduruyoruz
        leftCIMMotor.set(0.0);//sol cim motorunu durduruyoruz
        rightCIMMotor.set(0.0);//sağ cim motorunu durduruyoruz
      }
      // Pressed - 1 Part working
      case 0 -> { //0 olması durumunda
        intakeNEO.set(0.0); //intake motorunu durduruyoruz
        leftCIMMotor.set(0.9);//sol cim motorunu 0.9 da çalıştırıyoruz
        rightCIMMotor.set(0.9);//sağ cim motorunu 0.9 da çalıştırıyoruz
      }
      case 90 -> { //90 olması durumunda
          intakeNEO.set(1); //intake motorunu 1 de çalıştırıyoruz
        leftCIMMotor.set(0.0);//sol cim motorunu durduruyoruz
        rightCIMMotor.set(0.0);//sağ cim motorunu durduruyoruz
      }
      case 180 -> { //180 olması durumunda
        intakeNEO.set(0.0); //intake motorunu durduruyoruz
          leftCIMMotor.set(-0.5); //sol cim motorunu -0.5 de çalıştırıyoruz
          rightCIMMotor.set(-0.5);//sağ cim motorunu -0.5 de çalıştırıyoruz
      }
      case 270 -> { //270 olması durumunda
        intakeNEO.set(-0.3); //intake motorunu -0.3 de çalıştırıyoruz
        leftCIMMotor.set(0.0); //sol cim motorunu durduruyoruz
        rightCIMMotor.set(0.0); //sağ cim motorunu durduruyoruz
      }
      // Pressed - 2 Part Working
      case 45 -> { //45 olması durumunda
        intakeNEO.set(0.7); //intake motorunu 0.7 de çalıştırıyoruz
        leftCIMMotor.set(0.9); //sol cim motorunu 0.9 da çalıştırıyoruz
        rightCIMMotor.set(0.9);//sağ cim motorunu 0.9 da çalıştırıyoruz
      }
      case 225 -> { //225 olması durumunda
        intakeNEO.set(-0.3); //intake motorunu -0.3 de çalıştırıyoruz
          leftCIMMotor.set(-0.5); //sol cim motorunu -0.5 de çalıştırıyoruz
          rightCIMMotor.set(-0.5); //sağ cim motorunu -0.5 de çalıştırıyoruz
      }
    }
  }
}
