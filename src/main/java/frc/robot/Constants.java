package frc.robot;

import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitKt;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitLengthModel;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitModel;

public class Constants {
    //NativeUnitModel tells me how many motor turns per wheel rotation 9.76:1
    public static NativeUnitModel<Length> nativeUnitModel = new NativeUnitLengthModel(
            NativeUnitKt.getSTU(9.76),
            LengthKt.getFeet(5)
    );
}
