/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.commands.ResettingTheNavX;
/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {

  private AHRS navX = new AHRS(edu.wpi.first.wpilibj.SPI.Port.kMXP);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   setDefaultCommand(new ResettingTheNavX());
  }
  
  public void resetpapa(){
    navX.reset();
  }
  public void swervePapa(Joystick joystick){
    double FWD = -1 * joystick.getY();
    double STR = joystick.getX();
    double RCW = joystick.getZ();
    double temp;
    final double L = 25;
    final double W = 3;
    final double R = Math.sqrt(Math.pow(L,2) + Math.pow(W,2));
    temp = FWD * Math.cos(navX.getAngle()) + STR * Math.sin(navX.getAngle());
    STR  = -1 * FWD * Math.sin(navX.getAngle()) +  STR * Math.cos(navX.getAngle());
    FWD = temp;
    double A = STR - RCW*(L/R);
    double B = STR + RCW*(L/R);
    double C = FWD - RCW*(W/R);
    double D = FWD + RCW*(W/R);

    double ws1 = Math.sqrt(Math.pow(B, 2) + Math.pow(C, 2) ); double wa1 = Math.atan2(B,C)*180/Math.PI;
    double ws2 = Math.sqrt(Math.pow(B, 2) + Math.pow(D, 2) ); double wa2 = Math.atan2(B,D)*180/Math.PI;
    double ws3 = Math.sqrt(Math.pow(A, 2) + Math.pow(D, 2) ); double wa3 = Math.atan2(A,D)*180/Math.PI;
    double ws4 = Math.sqrt(Math.pow(A, 2) + Math.pow(C, 2) ); double wa4 = Math.atan2(A,C)*180/Math.PI;

    double max = Math.max(ws1, Math.max(ws2, Math.max(ws3, ws4)));
    if(max>1){ws1/=max; ws2/=max; ws3/=max; ws4/=max;}
    

  }
}
