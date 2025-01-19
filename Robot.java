//10. satıra kadar olan kısım wpilib kütüphanesindeki kodları kullanmamız için onları çağırmamızı sağlayan kodlar.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShootingSubsystem;


public class Robot extends TimedRobot {
  private XboxController firstDriverController; //Burda robotu kontrol etmek için kullanılan birinci xbox kontrülcüsünü tanımladık
  private XboxController secondDriverController; //Bu da aynısının ikincisi
  private DrivetrainSubsystem m_drivetrain_test; //Burda robotun hareketlerini kontrol etmek için DrivetrainSubsystem ı tanımladık
  private ShootingSubsystem m_intake_encoder; //Burda encoderi kontrol etmek için atış sistemini tanımladık
  private double pos; //Robotun pozisyonunu kontrol etmek için bi değişken tanımladık
  private int task; //Robotun görevini tanımladık
  private int povValue; //Robotun yönünün değerini tanımladık
  private Timer timer; //Zamanlayıcı tanımladık işte


  @Override
  public void robotInit() {
 
    firstDriverController = new XboxController(Constants.ControllersConstants.FIRST_DRIVERS_CONTROLLER); //Birinci konsolu tanımladık
    secondDriverController = new XboxController(Constants.ControllersConstants.SECOND_DRIVERS_CONTROLLER); //İkinci konsolu tanımladık


    m_intake_encoder = new ShootingSubsystem(); //Atış sistemini tanımlama (encoderını fln)
    m_drivetrain_test = new DrivetrainSubsystem(); //Sürüş sistemini tanımladık

    pos = 0; //Başlangıçta pozisyonu 0 olarak verdik
    povValue = -1; //Yönü de eksi bir olarak verdik 
  }

  
  @Override
  public void robotPeriodic() { //(döngü içinde, sürekli olarak yapılacak olanlar)
   
    CommandScheduler.getInstance().run(); //komutları çalıştırdı
  }

  @Override
  public void disabledInit() {} //Boş

  @Override
  public void disabledPeriodic() {}//Boş


  @Override
  public void autonomousInit() { //Otonom modda başlangıçta yapılcaklar:
   
    timer = new Timer(); //Zamanlayıcı ayarladık

    timer.reset(); //sıfırladık
    timer.start(); //ve başlattık

    task = -1; //Görevi -1 olarak tanımladık (neden -1?)
  }

  
  @Override
  public void autonomousPeriodic() {
    System.out.println(timer.get()); //Zamanı ekrana yazdır

    m_intake_encoder.autoPos(pos); //Encoder la atış sistemini kontrol et (poziyonu)
    m_intake_encoder.getPOVValues(povValue); //Yönü al (encoderdan)

    
    if (0 < timer.get() && timer.get() < 1.5) { // 1.5 sec //Süre 0 la 1.5 arasındaysa (saniye), görevin değerini bir yap
      task = 1;
    }
    if (1.5 < timer.get() && timer.get() < 2) { // 0.5 sec // 1.5 ile 2 arasındaysa görevin değerini 2 yap
      task = 2;
    }
    if (2 < timer.get() && timer.get() < 3.5) { // 1.5 sec // 2 ile 3.5 arasındaysa görevin değerini 3 yap
      task = 3;
    }
    if (3.5 < timer.get() && timer.get() < 4.5) { // 1 sec // 3.5 ile 4.5 arasındaysa görevin değerini 4 yap
      task = 4;
    }
    if (4.5 < timer.get() && timer.get() < 6) { // 1.5 sec // 4.5 ile 6 arasındaysa görevin değerini 5 yap
      task = 5;
    }
    if (6.5 < timer.get() && timer.get() < 7) { // 0.5 sec // 6.5 ile 7 arasındaysa görevin değerini 6 yap
      task = 6;
    }
    if (7 < timer.get() && timer.get() < 8.5) { // 1.5 sec // 7 ile 8.5 arasındaysa görevin değerini 7 yap
      task = 7;
    }
    if (8.5 < timer.get()) { // Last Task // değer tüm bunların üzerindeyse görevin değerini 8 yap
      task = 8;
    }
    System.out.println(task); //vee görevi yazdır

    
    switch (task) { //görevin değerine göre yapılacak bi kaç değişiklik var, onları ayarlıcaz:
      case 1 -> { 
        povValue = 0; //Görevin değerinin 1 olduğu durumlarda yönü 0 yap
        pos = -1.1; // ve poziyonu da -1.1 yap
      }
      case 2 -> { //Görevin değerinin 2 olduğu durumlarda:
        povValue = -1; //Yön -1
        m_drivetrain_test.driveBoth(-0.7, 0); //ve sürüş sistemi ile robotu -0.7,0'a  doğru hareket ettir 
      }
      case 3 -> m_drivetrain_test.driveBoth(0, -0.7); //Görevin 3 olduğu durumlarda robotu 0,-0.7'e doğru hareker ettir
      case 4 -> povValue = 270;//Görevin 4 olduğu durumlarda yönü 270  yap
      case 5 -> { //Görevin 5 olduğu durumlarda:
        povValue = -1; //Yönü -1 yap
        m_drivetrain_test.driveBoth(0, 0.7); //ve robotu 0,0.7'ye doğru hareket ettir
        pos = 0; //poziyonu 0 yap
      }
      case 6 -> { //Görevin 6 olduğu durumlarda:
        m_drivetrain_test.driveBoth(0.7, 0); //robotu 0.7,0'a doğru hareket ettir
        pos = 0; //poziyonu 0 yap
      }
      case 8 -> pos = 0; //Görevin 8 olduğu durumlarda poziyonu 0 yap
      case 9 -> povValue = 45; //Görevin 9 olduğu durumlarda yönü 45 yap
      case 10 -> { //Görevin 10 olduğu durumlarda:
        povValue = -1; //yönü -1 yap
        System.out.println("Program Stopped"); //ve "program durdu" yazdır
      }
    }
  }

  @Override//
  public void teleopInit() { //boş

  }

  
  @Override
  public void teleopPeriodic() { //teleop modda sürekli tekrarlancaklar
   
    double spd =  firstDriverController.getRightX(); //birinci konsoldan hızı al
    double rotation = firstDriverController.getLeftY(); //birinci konsoldan yönü al
    
    povValue = secondDriverController.getPOV(); //ikinci konsoldan yönün değerini al
   
    m_drivetrain_test.driveBoth(spd, rotation); //sürüş sistemiyle robotu hareket ettir
    m_intake_encoder.autoPos(pos); //atış sistemini çalıştır (encoderla)
    
    m_intake_encoder.getPOVValues(povValue); //encoderdan yönü al

  
    if (firstDriverController.getBButton()) {  //birinci kullanıcıda B butonu basılıysaaa:
      pos = 0; //pozisyonu zero yapp
    }
    if (firstDriverController.getYButton()) { //birinci kullanıcıda Y butonu basılıysaaa:
     
      pos = -0.45; //pozisyonu -0.45 yappp
    }
    if (firstDriverController.getXButton()){ //birinci kullanıcıda X butonu basılıysaaa:
     
      pos = -1.1; //pozisyonu -1.1 yappp
    }
  } //yuppii

  @Override
  public void testInit() { //test modu, döndü içinde olmayan kısım:
    
    
    CommandScheduler.getInstance().cancelAll(); //tüm komutları durdur, iptal et.

  }

  @Override
  public void testPeriodic() { //test modunda döngü içindekiler:
    double rotation = firstDriverController.getLeftY(); //birinci kullanıcıdan yönü all

    m_intake_encoder.encoderReadout(rotation); //encoderı oku ve yönü al
  }

 
  @Override
  public void simulationInit() {} //boş

  @Override
  public void simulationPeriodic() {}//boş
}

//Bitttiiiiii :))
