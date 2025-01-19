//Bu yapı video serisinin 5. videosunda anlatılan kodlarda karışıklık olmaması için farklı işlemleri farklı sayfalarda yaptırtan yapı, aslında yine
//aynı işlemler yapılıyo ama daha düzenli oluyo işte

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class DrivetrainSubsystem {
    private final DifferentialDrive frontDrive = Constants.FRONT_DRIVE;//ön sürüş sistemi tanımlandı
    private final DifferentialDrive rearDrive = Constants.REAR_DRIVE;//arka sürüş sistemi tanımlandı
    
    public void arcadeDrv(double spd, double rotation, DifferentialDrive drive){
        drive.arcadeDrive(spd, rotation, true); //hız ve dönüş değerlerini karesel sayı olarak ayarladık (önemli)
        //girdilerin karesel sayı olması hareketlerin daha yumuşak olmasını sağlayarak robotun dönüşlerde ve hareketlerde daha kontrollü olmasını sağlar
    }

    public void driveBoth(double spd, double rotation){ //hız ve dönüş değerleri için:
        arcadeDrv(spd,rotation,frontDrive); //ön sürüş sisteminde hız ve dönüş
        arcadeDrv(spd,rotation,rearDrive); //arka sürüş sisteminde hız ve dönüş
    }
}
