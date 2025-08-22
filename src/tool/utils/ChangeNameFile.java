package tool.utils;
import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
public class ChangeNameFile
{
public static void main(String[] args) {
try {
/* 16 */       String folderName = "C:\\Users\\admin\\Desktop\\cbro\\data\\Twilight\\res\\x1 - Copy";
/* 17 */       String newFolderName = "C:\\Users\\admin\\Desktop\\test752002";
/* 18 */       File folder = new File(folderName);
/* 19 */       if (!folder.exists()) {
return;
}
/* 22 */       File newFolder = new File(newFolderName);
/* 23 */       if (!newFolder.exists()) {
/* 24 */         newFolder.mkdirs();
}
/* 26 */       for (File file : folder.listFiles()) {
/* 27 */         String fileName = file.getName();
/* 28 */         fileName = fileName + ".png";
/* 29 */         File newFile = new File(newFolder.getAbsoluteFile() + "/" + fileName);
/* 30 */         Files.copy(file.toPath(), newFile.toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
}
/* 32 */       System.out.println("done");
/* 33 */     } catch (Exception e) {
/* 34 */       e.printStackTrace();
}
}
}



