import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\work\\redis-demo");
        // 层级打印
        // printFiles(file, 0);
        // 字节流
        byte[] bytes = {40, 41, 42, 43, 44, 45, 46, 47, 48, 49};
        // 内存字节模拟
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        readFile(byteArrayInputStream);
    }

    private static void printFiles(File file, int level) {
        // 打印缩进
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        // 打印当前文件
        System.out.println(file.getName() + (file.isDirectory() ? "/" : ""));

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                printFiles(f, level + 1);
            }
        }
    }
    private static void readFile(InputStream inputStream) throws IOException {
        int n;
        StringBuilder sb = new StringBuilder();
        while ((n = inputStream.read()) != -1) {
            sb.append((char) n);
        }
        System.out.println(sb.toString());
    }
}
