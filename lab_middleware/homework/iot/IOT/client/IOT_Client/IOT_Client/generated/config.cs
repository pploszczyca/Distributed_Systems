//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.7
//
// <auto-generated>
//
// Generated from file `config.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//


using _System = global::System;

#pragma warning disable 1591

namespace config
{
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1704")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1707")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1709")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1710")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1711")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1715")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1716")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1720")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1722")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1724")]
    [global::System.Serializable]
    public partial class ObjectInformation : global::System.ICloneable
    {
        #region Slice data members

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public string category;

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public string name;

        #endregion

        partial void ice_initialize();

        #region Constructors

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public ObjectInformation()
        {
            this.category = "";
            this.name = "";
            ice_initialize();
        }

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public ObjectInformation(string category, string name)
        {
            this.category = category;
            this.name = name;
            ice_initialize();
        }

        #endregion

        #region ICloneable members

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public object Clone()
        {
            return MemberwiseClone();
        }

        #endregion

        #region Object members

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public override int GetHashCode()
        {
            int h_ = 5381;
            global::IceInternal.HashUtil.hashAdd(ref h_, "::config::ObjectInformation");
            global::IceInternal.HashUtil.hashAdd(ref h_, category);
            global::IceInternal.HashUtil.hashAdd(ref h_, name);
            return h_;
        }

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public override bool Equals(object other)
        {
            if (object.ReferenceEquals(this, other))
            {
                return true;
            }

            if (other == null)
            {
                return false;
            }

            if (GetType() != other.GetType())
            {
                return false;
            }

            ObjectInformation o = (ObjectInformation) other;
            if (this.category == null)
            {
                if (o.category != null)
                {
                    return false;
                }
            }
            else
            {
                if (!this.category.Equals(o.category))
                {
                    return false;
                }
            }

            if (this.name == null)
            {
                if (o.name != null)
                {
                    return false;
                }
            }
            else
            {
                if (!this.name.Equals(o.name))
                {
                    return false;
                }
            }

            return true;
        }

        #endregion

        #region Comparison members

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public static bool operator ==(ObjectInformation lhs, ObjectInformation rhs)
        {
            return Equals(lhs, rhs);
        }

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public static bool operator !=(ObjectInformation lhs, ObjectInformation rhs)
        {
            return !Equals(lhs, rhs);
        }

        #endregion

        #region Marshaling support

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public void ice_writeMembers(global::Ice.OutputStream ostr)
        {
            ostr.writeString(this.category);
            ostr.writeString(this.name);
        }

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public void ice_readMembers(global::Ice.InputStream istr)
        {
            this.category = istr.readString();
            this.name = istr.readString();
        }

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public static void ice_write(global::Ice.OutputStream ostr, ObjectInformation v)
        {
            if (v == null)
            {
                _nullMarshalValue.ice_writeMembers(ostr);
            }
            else
            {
                v.ice_writeMembers(ostr);
            }
        }

        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        public static ObjectInformation ice_read(global::Ice.InputStream istr)
        {
            var v = new ObjectInformation();
            v.ice_readMembers(istr);
            return v;
        }

        private static readonly ObjectInformation _nullMarshalValue = new ObjectInformation();

        #endregion
    }

    [global::System.Runtime.InteropServices.ComVisible(false)]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1704")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1707")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1709")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1710")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1711")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1715")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1716")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1720")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1722")]
    [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Naming", "CA1724")]
    public partial interface ObjectInfoService : global::Ice.Object, ObjectInfoServiceOperations_
    {
    }
}

namespace config
{
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
    public delegate void Callback_ObjectInfoService_getObjectInformations(ObjectInformation[] ret);
}

namespace config
{
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
    public interface ObjectInfoServicePrx : global::Ice.ObjectPrx
    {
        ObjectInformation[] getObjectInformations(
            global::Ice.OptionalContext context = new global::Ice.OptionalContext());

        global::System.Threading.Tasks.Task<ObjectInformation[]> getObjectInformationsAsync(
            global::Ice.OptionalContext context = new global::Ice.OptionalContext(),
            global::System.IProgress<bool> progress = null,
            global::System.Threading.CancellationToken cancel = new global::System.Threading.CancellationToken());

        global::Ice.AsyncResult<Callback_ObjectInfoService_getObjectInformations> begin_getObjectInformations(
            global::Ice.OptionalContext context = new global::Ice.OptionalContext());

        global::Ice.AsyncResult begin_getObjectInformations(global::Ice.AsyncCallback callback, object cookie);

        global::Ice.AsyncResult begin_getObjectInformations(global::Ice.OptionalContext context,
            global::Ice.AsyncCallback callback, object cookie);

        ObjectInformation[] end_getObjectInformations(global::Ice.AsyncResult asyncResult);
    }
}

namespace config
{
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
    public interface ObjectInfoServiceOperations_
    {
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
        ObjectInformation[] getObjectInformations(global::Ice.Current current = null);
    }
}

namespace config
{
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
    public sealed class ObjectInformationSequenceHelper
    {
        public static void write(global::Ice.OutputStream ostr, ObjectInformation[] v)
        {
            if (v == null)
            {
                ostr.writeSize(0);
            }
            else
            {
                ostr.writeSize(v.Length);
                for (int ix = 0; ix < v.Length; ++ix)
                {
                    (v[ix] == null ? new ObjectInformation() : v[ix]).ice_writeMembers(ostr);
                }
            }
        }

        public static ObjectInformation[] read(global::Ice.InputStream istr)
        {
            ObjectInformation[] v;
            {
                int szx = istr.readAndCheckSeqSize(2);
                v = new ObjectInformation[szx];
                for (int ix = 0; ix < szx; ++ix)
                {
                    v[ix] = new ObjectInformation();
                    v[ix].ice_readMembers(istr);
                }
            }
            return v;
        }
    }

    [global::System.Runtime.InteropServices.ComVisible(false)]
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
    [global::System.Serializable]
    public sealed class ObjectInfoServicePrxHelper : global::Ice.ObjectPrxHelperBase, ObjectInfoServicePrx
    {
        public ObjectInfoServicePrxHelper()
        {
        }

        public ObjectInfoServicePrxHelper(global::System.Runtime.Serialization.SerializationInfo info,
            global::System.Runtime.Serialization.StreamingContext context) : base(info, context)
        {
        }

        #region Synchronous operations

        public ObjectInformation[] getObjectInformations(
            global::Ice.OptionalContext context = new global::Ice.OptionalContext())
        {
            try
            {
                return _iceI_getObjectInformationsAsync(context, null, global::System.Threading.CancellationToken.None,
                    true).Result;
            }
            catch (global::System.AggregateException ex_)
            {
                throw ex_.InnerException;
            }
        }

        #endregion

        #region Async Task operations

        public global::System.Threading.Tasks.Task<ObjectInformation[]> getObjectInformationsAsync(
            global::Ice.OptionalContext context = new global::Ice.OptionalContext(),
            global::System.IProgress<bool> progress = null,
            global::System.Threading.CancellationToken cancel = new global::System.Threading.CancellationToken())
        {
            return _iceI_getObjectInformationsAsync(context, progress, cancel, false);
        }

        private global::System.Threading.Tasks.Task<ObjectInformation[]> _iceI_getObjectInformationsAsync(
            global::Ice.OptionalContext context, global::System.IProgress<bool> progress,
            global::System.Threading.CancellationToken cancel, bool synchronous)
        {
            iceCheckTwowayOnly(_getObjectInformations_name);
            var completed =
                new global::IceInternal.OperationTaskCompletionCallback<ObjectInformation[]>(progress, cancel);
            _iceI_getObjectInformations(context, synchronous, completed);
            return completed.Task;
        }

        private const string _getObjectInformations_name = "getObjectInformations";

        private void _iceI_getObjectInformations(global::System.Collections.Generic.Dictionary<string, string> context,
            bool synchronous, global::IceInternal.OutgoingAsyncCompletionCallback completed)
        {
            var outAsync = getOutgoingAsync<ObjectInformation[]>(completed);
            outAsync.invoke(
                _getObjectInformations_name,
                global::Ice.OperationMode.Normal,
                global::Ice.FormatType.DefaultFormat,
                context,
                synchronous,
                read: (global::Ice.InputStream istr) =>
                {
                    ObjectInformation[] ret;
                    ret = ObjectInformationSequenceHelper.read(istr);
                    return ret;
                });
        }

        #endregion

        #region Asynchronous operations

        public global::Ice.AsyncResult<Callback_ObjectInfoService_getObjectInformations> begin_getObjectInformations(
            global::Ice.OptionalContext context = new global::Ice.OptionalContext())
        {
            return begin_getObjectInformations(context, null, null, false);
        }

        public global::Ice.AsyncResult begin_getObjectInformations(global::Ice.AsyncCallback callback, object cookie)
        {
            return begin_getObjectInformations(new global::Ice.OptionalContext(), callback, cookie, false);
        }

        public global::Ice.AsyncResult begin_getObjectInformations(global::Ice.OptionalContext context,
            global::Ice.AsyncCallback callback, object cookie)
        {
            return begin_getObjectInformations(context, callback, cookie, false);
        }

        public ObjectInformation[] end_getObjectInformations(global::Ice.AsyncResult asyncResult)
        {
            var resultI_ = global::IceInternal.AsyncResultI.check(asyncResult, this, _getObjectInformations_name);
            var outgoing_ = (global::IceInternal.OutgoingAsyncT<ObjectInformation[]>) resultI_.OutgoingAsync;
            return outgoing_.getResult(resultI_.wait());
        }

        private global::Ice.AsyncResult<Callback_ObjectInfoService_getObjectInformations> begin_getObjectInformations(
            global::System.Collections.Generic.Dictionary<string, string> context,
            global::Ice.AsyncCallback completedCallback, object cookie, bool synchronous)
        {
            iceCheckAsyncTwowayOnly(_getObjectInformations_name);
            var completed =
                new global::IceInternal.OperationAsyncResultCompletionCallback<
                    Callback_ObjectInfoService_getObjectInformations, ObjectInformation[]>(
                    (Callback_ObjectInfoService_getObjectInformations cb, ObjectInformation[] ret) =>
                    {
                        if (cb != null)
                        {
                            cb.Invoke(ret);
                        }
                    },
                    this, _getObjectInformations_name, cookie, completedCallback);
            _iceI_getObjectInformations(context, synchronous, completed);
            return completed;
        }

        #endregion

        #region Checked and unchecked cast operations

        public static ObjectInfoServicePrx checkedCast(global::Ice.ObjectPrx b)
        {
            if (b == null)
            {
                return null;
            }

            ObjectInfoServicePrx r = b as ObjectInfoServicePrx;
            if ((r == null) && b.ice_isA(ice_staticId()))
            {
                ObjectInfoServicePrxHelper h = new ObjectInfoServicePrxHelper();
                h.iceCopyFrom(b);
                r = h;
            }

            return r;
        }

        public static ObjectInfoServicePrx checkedCast(global::Ice.ObjectPrx b,
            global::System.Collections.Generic.Dictionary<string, string> ctx)
        {
            if (b == null)
            {
                return null;
            }

            ObjectInfoServicePrx r = b as ObjectInfoServicePrx;
            if ((r == null) && b.ice_isA(ice_staticId(), ctx))
            {
                ObjectInfoServicePrxHelper h = new ObjectInfoServicePrxHelper();
                h.iceCopyFrom(b);
                r = h;
            }

            return r;
        }

        public static ObjectInfoServicePrx checkedCast(global::Ice.ObjectPrx b, string f)
        {
            if (b == null)
            {
                return null;
            }

            global::Ice.ObjectPrx bb = b.ice_facet(f);
            try
            {
                if (bb.ice_isA(ice_staticId()))
                {
                    ObjectInfoServicePrxHelper h = new ObjectInfoServicePrxHelper();
                    h.iceCopyFrom(bb);
                    return h;
                }
            }
            catch (global::Ice.FacetNotExistException)
            {
            }

            return null;
        }

        public static ObjectInfoServicePrx checkedCast(global::Ice.ObjectPrx b, string f,
            global::System.Collections.Generic.Dictionary<string, string> ctx)
        {
            if (b == null)
            {
                return null;
            }

            global::Ice.ObjectPrx bb = b.ice_facet(f);
            try
            {
                if (bb.ice_isA(ice_staticId(), ctx))
                {
                    ObjectInfoServicePrxHelper h = new ObjectInfoServicePrxHelper();
                    h.iceCopyFrom(bb);
                    return h;
                }
            }
            catch (global::Ice.FacetNotExistException)
            {
            }

            return null;
        }

        public static ObjectInfoServicePrx uncheckedCast(global::Ice.ObjectPrx b)
        {
            if (b == null)
            {
                return null;
            }

            ObjectInfoServicePrx r = b as ObjectInfoServicePrx;
            if (r == null)
            {
                ObjectInfoServicePrxHelper h = new ObjectInfoServicePrxHelper();
                h.iceCopyFrom(b);
                r = h;
            }

            return r;
        }

        public static ObjectInfoServicePrx uncheckedCast(global::Ice.ObjectPrx b, string f)
        {
            if (b == null)
            {
                return null;
            }

            global::Ice.ObjectPrx bb = b.ice_facet(f);
            ObjectInfoServicePrxHelper h = new ObjectInfoServicePrxHelper();
            h.iceCopyFrom(bb);
            return h;
        }

        private static readonly string[] _ids =
        {
            "::Ice::Object",
            "::config::ObjectInfoService"
        };

        public static string ice_staticId()
        {
            return _ids[1];
        }

        #endregion

        #region Marshaling support

        public static void write(global::Ice.OutputStream ostr, ObjectInfoServicePrx v)
        {
            ostr.writeProxy(v);
        }

        public static ObjectInfoServicePrx read(global::Ice.InputStream istr)
        {
            global::Ice.ObjectPrx proxy = istr.readProxy();
            if (proxy != null)
            {
                ObjectInfoServicePrxHelper result = new ObjectInfoServicePrxHelper();
                result.iceCopyFrom(proxy);
                return result;
            }

            return null;
        }

        #endregion
    }
}

namespace config
{
    [global::System.Runtime.InteropServices.ComVisible(false)]
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("slice2cs", "3.7.7")]
    public abstract class ObjectInfoServiceDisp_ : global::Ice.ObjectImpl, ObjectInfoService
    {
        #region Slice operations

        public abstract ObjectInformation[] getObjectInformations(global::Ice.Current current = null);

        #endregion

        #region Slice type-related members

        private static readonly string[] _ids =
        {
            "::Ice::Object",
            "::config::ObjectInfoService"
        };

        public override bool ice_isA(string s, global::Ice.Current current = null)
        {
            return global::System.Array.BinarySearch(_ids, s, IceUtilInternal.StringUtil.OrdinalStringComparer) >= 0;
        }

        public override string[] ice_ids(global::Ice.Current current = null)
        {
            return _ids;
        }

        public override string ice_id(global::Ice.Current current = null)
        {
            return _ids[1];
        }

        public static new string ice_staticId()
        {
            return _ids[1];
        }

        #endregion

        #region Operation dispatch

        [global::System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Design", "CA1011")]
        public static global::System.Threading.Tasks.Task<global::Ice.OutputStream>
            iceD_getObjectInformations(ObjectInfoService obj, global::IceInternal.Incoming inS,
                global::Ice.Current current)
        {
            global::Ice.ObjectImpl.iceCheckMode(global::Ice.OperationMode.Normal, current.mode);
            inS.readEmptyParams();
            var ret = obj.getObjectInformations(current);
            var ostr = inS.startWriteParams();
            ObjectInformationSequenceHelper.write(ostr, ret);
            inS.endWriteParams(ostr);
            return inS.setResult(ostr);
        }

        private static readonly string[] _all =
        {
            "getObjectInformations",
            "ice_id",
            "ice_ids",
            "ice_isA",
            "ice_ping"
        };

        public override global::System.Threading.Tasks.Task<global::Ice.OutputStream>
            iceDispatch(global::IceInternal.Incoming inS, global::Ice.Current current)
        {
            int pos = global::System.Array.BinarySearch(_all, current.operation,
                global::IceUtilInternal.StringUtil.OrdinalStringComparer);
            if (pos < 0)
            {
                throw new global::Ice.OperationNotExistException(current.id, current.facet, current.operation);
            }

            switch (pos)
            {
                case 0:
                {
                    return iceD_getObjectInformations(this, inS, current);
                }
                case 1:
                {
                    return global::Ice.ObjectImpl.iceD_ice_id(this, inS, current);
                }
                case 2:
                {
                    return global::Ice.ObjectImpl.iceD_ice_ids(this, inS, current);
                }
                case 3:
                {
                    return global::Ice.ObjectImpl.iceD_ice_isA(this, inS, current);
                }
                case 4:
                {
                    return global::Ice.ObjectImpl.iceD_ice_ping(this, inS, current);
                }
            }

            global::System.Diagnostics.Debug.Assert(false);
            throw new global::Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        #endregion
    }
}