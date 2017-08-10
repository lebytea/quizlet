package loader;

import com.quizlet.loader.AppLoaderAnyData;
import org.junit.Test;
import static org.junit.Assert.*;
public class Test_AppLoaderAnyData {

    @Test
    public void doExample1(){

        try {
            Test_manual_AppLoaderResource_And_FileParser myExtream = new Test_manual_AppLoaderResource_And_FileParser();
            String pathSource = ".\\test\\quizlet\\loader\\test_parser_quiz_1";//one example quiz
            myExtream.jTest_AppLoaderAnyData(pathSource);
        }catch (Exception e){
            throw new AssertionError();
        }

    }
    @Test
    public void doExample2(){

        try {
            AppLoaderAnyData myExtream = new AppLoaderAnyData();
            String pathSource = ".\\test\\quizlet\\loader\\test_parser_quiz_2";//one example quiz
            myExtream.loadQuizItems(pathSource);
        }catch (Exception e){
            throw new AssertionError();
        }

    }
    @Test
    public void someExample() {

        int res = 7;
        assertEquals("There is some problem in ... ", res, 7);
    }
}
