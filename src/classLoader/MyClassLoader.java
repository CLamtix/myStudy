package classLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader{
	
	public String classDir;
	
	public MyClassLoader() {
		
	}
	
	public MyClassLoader(String str){
		classDir = str;
	}
	@SuppressWarnings("deprecation")
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String classFilePath = classDir + "\\" + name + ".class";
		try {
			FileInputStream fis = new FileInputStream(classFilePath);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			encodeAndDecode(fis,baos);
			byte[] bytes = baos.toByteArray();
			return defineClass(bytes, 0, bytes.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}

	public static void encodeAndDecode(InputStream fis, OutputStream os) throws IOException {
		int b = -1;
		while((b=fis.read())!=-1){
			os.write(b ^ 0xff);
		}
	}
	public static void main(String[] args) throws IOException {
		String srcPath = args[0];
		String desPath = args[1];
		String desFileName = srcPath.substring(srcPath.lastIndexOf("\\")+1);
		String desPathFile = desPath + "\\" + desFileName;
		
		FileInputStream fis = new FileInputStream(srcPath);
		FileOutputStream fos = new FileOutputStream(desPathFile);
		encodeAndDecode(fis, fos);
		fis.close();
		fos.close();
	}
}
