//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.7
//
// <auto-generated>
//
// Generated from file `frige.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package device.fridge;

public interface BasicFridge extends com.zeroc.Ice.Object
{
    void addProduct(Product product, com.zeroc.Ice.Current current)
        throws BadDateFormat,
               NoSpace;

    Product takeProductOutByName(String productName, com.zeroc.Ice.Current current)
        throws NoProduct;

    Product[] getProductsIn(com.zeroc.Ice.Current current);

    int numberOfProductsIn(com.zeroc.Ice.Current current);

    void setFreezingLevel(FreezingLevel freezingLevel, com.zeroc.Ice.Current current);

    FreezingLevel getFreezingLevel(com.zeroc.Ice.Current current);

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::device::fridge::BasicFridge"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::device::fridge::BasicFridge";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_addProduct(BasicFridge obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        Product iceP_product;
        iceP_product = Product.ice_read(istr);
        inS.endReadParams();
        obj.addProduct(iceP_product, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_takeProductOutByName(BasicFridge obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_productName;
        iceP_productName = istr.readString();
        inS.endReadParams();
        Product ret = obj.takeProductOutByName(iceP_productName, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        Product.ice_write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_getProductsIn(BasicFridge obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        Product[] ret = obj.getProductsIn(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ProductsHelper.write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_numberOfProductsIn(BasicFridge obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        int ret = obj.numberOfProductsIn(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeInt(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_setFreezingLevel(BasicFridge obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        FreezingLevel iceP_freezingLevel;
        iceP_freezingLevel = FreezingLevel.ice_read(istr);
        inS.endReadParams();
        obj.setFreezingLevel(iceP_freezingLevel, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_getFreezingLevel(BasicFridge obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        FreezingLevel ret = obj.getFreezingLevel(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        FreezingLevel.ice_write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "addProduct",
        "getFreezingLevel",
        "getProductsIn",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "numberOfProductsIn",
        "setFreezingLevel",
        "takeProductOutByName"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return _iceD_addProduct(this, in, current);
            }
            case 1:
            {
                return _iceD_getFreezingLevel(this, in, current);
            }
            case 2:
            {
                return _iceD_getProductsIn(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 5:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 6:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 7:
            {
                return _iceD_numberOfProductsIn(this, in, current);
            }
            case 8:
            {
                return _iceD_setFreezingLevel(this, in, current);
            }
            case 9:
            {
                return _iceD_takeProductOutByName(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}