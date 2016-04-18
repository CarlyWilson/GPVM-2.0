package gpvm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Brian
 */

/**
 * UnitTest for the two pass assembler.
 */
public class AssmTest {
    
    // number of passed tests
    static int passed = 0;
    
    // number of tests attempted
    static int att = 0;
    
    public static void runTest(String src, int exp, String caseNum) throws FileNotFoundException, UnsupportedEncodingException{
        
        System.out.println("Test ID: "+caseNum);
        att++;
        
        String fileName = "test.txt";
        
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.print(src);
        writer.close();
        
        String [] prg = GPVMAssm.read(fileName);
        GPVM gPVM = new GPVM();
                
        gPVM.addOpCode(new ADD());
        gPVM.addOpCode(new ADDF());
        gPVM.addOpCode(new AND());
        gPVM.addOpCode(new BOO());
        gPVM.addOpCode(new CLL());
        gPVM.addOpCode(new CONCAT());
        gPVM.addOpCode(new DIV());
        gPVM.addOpCode(new DIVF());
        gPVM.addOpCode(new DPSH());
        gPVM.addOpCode(new DUP());
        gPVM.addOpCode(new F2I());
        gPVM.addOpCode(new F2S());
        gPVM.addOpCode(new HLT());
        gPVM.addOpCode(new I2F());
        gPVM.addOpCode(new I2S());
        gPVM.addOpCode(new J());
        gPVM.addOpCode(new JP());
        gPVM.addOpCode(new JZ());
        gPVM.addOpCode(new LD());
        gPVM.addOpCode(new LDC());
        gPVM.addOpCode(new LDF());
        gPVM.addOpCode(new LDI());
        gPVM.addOpCode(new LDR());
        gPVM.addOpCode(new LDS());
        gPVM.addOpCode(new MOD());
        gPVM.addOpCode(new MUL());
        gPVM.addOpCode(new MULF());
        gPVM.addOpCode(new NEG());
        gPVM.addOpCode(new NEGF());
        gPVM.addOpCode(new NOT());
        gPVM.addOpCode(new OR ());
        gPVM.addOpCode(new POP());
        gPVM.addOpCode(new PSH());
        gPVM.addOpCode(new RET());
        gPVM.addOpCode(new S2F());
        gPVM.addOpCode(new S2I());
        gPVM.addOpCode(new SGN());
        gPVM.addOpCode(new SIN());
        gPVM.addOpCode(new SOUT());
        gPVM.addOpCode(new ST());
        gPVM.addOpCode(new STF());
        gPVM.addOpCode(new STI());
        gPVM.addOpCode(new STR());
        gPVM.addOpCode(new STS());
        gPVM.addOpCode(new SUB());
        gPVM.addOpCode(new SUBF());
        gPVM.addOpCode(new XOR());
        
        GPVMAssm assm = new GPVMAssm(gPVM);
        
        int []obj = assm.assemble(prg);
        
        int act = Integer.parseInt(gPVM.calculate(obj, new int[10], new String[10], 100, "-1"));
        
        System.out.print("Expected: "+exp+" Actual: "+act+" Result: ");
        if(exp==act){
            System.out.println("PASSED");
            passed++;
        }
        else System.out.println("FAILED");
        
        System.out.println(" ");
    }
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        
        String test01 = "psh\n100\npsh\n10\nadd\nhlt";
        runTest(test01, 110, "01");
        
        String test02 = "dpsh\n1000\n10000\nsub\nhlt";
        runTest(test02, -9000, "02");
        
        String test03 = "dpsh\n0\n13\ndiv\nhlt";
        runTest(test03, 0, "03");
        
        String test04 = "psh\nOne: 1\npsh\nTwo: 2\nadd\nhlt";
        runTest(test04, 3, "04");
        
        String test05 = "psh\n5\npsh\n10\nmul\nhlt";
        runTest(test05, 50, "05");
        
        String test06 = "psh\n 21\n neg\nhlt";
        runTest(test06, -21, "06");
        
        String test07 = "psh\n22\npsh\n3\nmod\nhlt";
        runTest(test07, 1, "07");
        
        String test08 = "dpsh\n10\n1\npop\nhlt";
        runTest(test08, 10, "08");
        
        String test09 = "psh\n-14551\nsgn\nhlt";
        runTest(test09, -1, "09");
        
        String test10 = "dpsh\n ONE:1 \n ONE \n add\nhlt";
        runTest(test10, 2, "10");
        
        String test11 = "dpsh\n 1\n 20\ndpsh\n 1\n 80\ndivf\nhlt";
        runTest(test11, 40, "11");
        
        String test12 = "dpsh \n 0 \n 0 \n add\n hlt";
        runTest(test12, 0, "12");
        
        String test13 = "dpsh \n 100 \n -100 \n sub \n hlt";
        runTest(test13, 200, "13");
        
        String test14 = "dpsh \n 1 \n -2147483646  \n sub \n hlt";
        runTest(test14, 2147483647, "14");
        
        String test15 = "dpsh \n 0 \n -2147483648 \n add \n hlt";
        runTest(test15, -2147483648, "15");
        
        String test16 = "dpsh \n 1 \n 1073741823 \n psh \n 2 \n mul \n add \n hlt";
        runTest(test16, 2147483647, "16");
        
        String test17 = "dpsh \n 1 \n 1 \n dpsh \n 1 \n 1 \n dpsh \n 4 \n 5 \n dpsh \n 2 \n 9 \n"
                + "dpsh \n 0 \n 9 \n dpsh \n 8 \n 7 \n hlt";
        runTest(test17, 7, "17");
        
        System.out.println("---------------------------------------------------");
        System.out.println("Tests Run: "+att+" Tests Passed: "+passed);
        System.out.println("---------------------------------------------------");

    }
}
