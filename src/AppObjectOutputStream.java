import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
public class AppObjectOutputStream extends ObjectOutputStream{
    public AppObjectOutputStream(OutputStream out) throws IOException{
        super(out);
    }
    @Override
    protected void writeStreamHeader()throws IOException{
        //no hace nada
    }
}

