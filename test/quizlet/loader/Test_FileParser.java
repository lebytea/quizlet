package loader;

import com.quizlet.loader.AppLoaderAnyData;
import com.quizlet.loader.FileParser;
import org.junit.Test;

/**
 * Created by Home on 10.08.2017.
 */
public class Test_FileParser {

    @Test
    public void doExample1(){

        try {
            Test_manual_AppLoaderResource_And_FileParser myExtream = new Test_manual_AppLoaderResource_And_FileParser();
            String pathSource = ".\\test\\quizlet\\loader\\test_parser_quiz_1";//one example quiz
            myExtream.jTest_FileParser(pathSource);
        }catch (Exception e){
            throw new AssertionError();
        }

    }
    @Test
    public void doExample2(){

        try {
            FileParser myExtream = new FileParser();
            String pathSource = ".\\test\\quizlet\\loader\\test_parser_quiz_2";//one example quiz
            myExtream.parseData(pathSource);
        }catch (Exception e){
            throw new AssertionError();
        }

    }
}
