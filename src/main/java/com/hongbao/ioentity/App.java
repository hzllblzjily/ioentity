package com.hongbao.ioentity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException
    {

        File path = new File(".");

        
        String[] listStrings = path.list();
        String s1 = "hzl";
        byte[] bytes = s1.getBytes("utf-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        bufferedInputStream.read(new byte[1000]);
        
        bufferedInputStream.close();
        
        
        FileOutputStream outputStream = new FileOutputStream("testout.txt");
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("file list");
        dataOutputStream.close();
        fileInputStream = new FileInputStream("testout.txt");
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        String str1 = dataInputStream.readUTF();
        
        
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        pipedInputStream.connect(pipedOutputStream);
        dataOutputStream.flush();
        dataInputStream = new DataInputStream(pipedInputStream);
        dataOutputStream = new DataOutputStream(pipedOutputStream);
        

        dataOutputStream.writeInt(100);
        int i = dataInputStream.readInt();

        FileReader fReader = new FileReader("test.txt");
        char[] charBuff = new char[1000];
        fReader.read(charBuff);
        
        fReader.close();
        StringReader stringReader = new StringReader("test.txt");
        BufferedReader bufferedReader = null;

        
        bufferedReader =   new BufferedReader(new InputStreamReader(System.in));

        
        //System.out.println(bufferedReader.readLine());
        
        FileChannel fChannel = new FileOutputStream("test.txt").getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1000);
        buf.asIntBuffer().put(1000);
        fChannel.write(ByteBuffer.wrap("Some text".getBytes()));
        fChannel.write(buf);
        fChannel.close();
        
        Entity entity = new Entity();
        entity.setI(1);
        entity.setS("hzl");
        
        ObjectOutputStream outputStream2 = new ObjectOutputStream(new FileOutputStream("obj.txt"));
        outputStream2.writeObject(entity);
        entity = new Entity();
        entity.setI(1);
        entity.setS("hzl");
        outputStream2.writeObject(entity);
        outputStream2.close();
        
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("obj.txt"));
        entity = (Entity) inputStream.readObject();
        Entity entity2 = (Entity) inputStream.readObject();
        inputStream.close();
       
        inputStream = new ObjectInputStream(new FileInputStream("obj.txt"));
        Entity entity3 = (Entity) inputStream.readObject();
        inputStream.close();
        
        
        
        
    }
}
