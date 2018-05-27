package demo.interview.com.demoapp.util;
/**
 * Created by ravindra on 14,May,2018
 */
public class GlobalError {

    private String message;

    private Error error;

    private String status;

    private String stack;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public Error getError ()
    {
        return error;
    }

    public void setError (Error error)
    {
        this.error = error;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getStack ()
    {
        return stack;
    }

    public void setStack (String stack)
    {
        this.stack = stack;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", error = "+error+", status = "+status+", stack = "+stack+"]";
    }

    public class Error
    {
        private String message;

        private String errorCode;

        public String getMessage ()
        {
            return message;
        }

        public void setMessage (String message)
        {
            this.message = message;
        }

        public String getErrorCode ()
        {
            return errorCode;
        }

        public void setErrorCode (String errorCode)
        {
            this.errorCode = errorCode;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [message = "+message+", errorCode = "+errorCode+"]";
        }
    }

}