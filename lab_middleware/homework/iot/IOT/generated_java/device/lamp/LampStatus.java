//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.7
//
// <auto-generated>
//
// Generated from file `lamp.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package device.lamp;

public enum LampStatus implements java.io.Serializable
{
    ON(100),
    OFF(0);

    public int value()
    {
        return _value;
    }

    public static LampStatus valueOf(int v)
    {
        switch(v)
        {
        case 100:
            return ON;
        case 0:
            return OFF;
        }
        return null;
    }

    private LampStatus(int v)
    {
        _value = v;
    }

    public void ice_write(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeEnum(_value, 100);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, LampStatus v)
    {
        if(v == null)
        {
            ostr.writeEnum(device.lamp.LampStatus.ON.value(), 100);
        }
        else
        {
            ostr.writeEnum(v.value(), 100);
        }
    }

    public static LampStatus ice_read(com.zeroc.Ice.InputStream istr)
    {
        int v = istr.readEnum(100);
        return validate(v);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<LampStatus> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, LampStatus v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            ice_write(ostr, v);
        }
    }

    public static java.util.Optional<LampStatus> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            return java.util.Optional.of(ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static LampStatus validate(int v)
    {
        final LampStatus e = valueOf(v);
        if(e == null)
        {
            throw new com.zeroc.Ice.MarshalException("enumerator value " + v + " is out of range");
        }
        return e;
    }

    private final int _value;
}