package com.kuroware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class KuroNote {
    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                  Welcome, This is KuroNote!");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        //Initialize Variables
        Scanner input = new Scanner(System.in);
        boolean shouldWeLoop;

        //Run tableOfContents() Method, Then loop until user hits exits ('N' or 'n')
        do {
            shouldWeLoop = tableOfContents(input);
            for (int i = 0; i < 100; ++i) { System.out.println(); } //Clear Screen
        }
        while (shouldWeLoop);

        //Close Scanner upon program exit
        input.close();
    }

    //Menu Methods
    private static void printMenu(){
        System.out.println("                                                        TABLE OF CONTENTS");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Chapter 0: IDE  & General Information                            || Chapter 6: Interfaces, Abstraction & Lambda Functions ");
        System.out.println("    [0.0] KuroNote Instructions                                  ||    [6.1] Enums");
        System.out.println("    [0.1] Hot Keys                                               ||    [6.2] Abstract Methods & Classes");
        System.out.println("    [0.2] Git/GitHub                                             ||    [6.3] Interfaces");
        System.out.println("    [0.3] Intellij Create .jar & .bat files                      ||    [6.4] Functional Interfaces & Anonymous Inner Class");
        System.out.println("    [0.4] Comments                                               ||    [6.5] Lambda Expressions");
        System.out.println("    [0.5] Naming Conventions                                     ||    [6.6] Keyword: instanceof");
        System.out.println("    [0.6] Unicode                                                || Chapter 7: Reading & Writing Files  ");
        System.out.println("Chapter 1: Java Fundamentals                                     ||    [7.1] File Systems Overview");
        System.out.println("    [1.1] Primitive Type (Domain, Range & Memory)                ||    [7.2] Text File Writing (.txt)");
        System.out.println("    [1.2] Remainder Operator (%)(Modulo)                         ||    [7.3] Text File Reading (.txt)");
        System.out.println("    [1.3] String : Escape Sequences                              ||    [7.4] Binary File Writing (.dat)");
        System.out.println("    [1.4] String : Methods                                       ||    [7.5] Binary File Reading (.dat)");
        System.out.println("    [1.5] String : Parse && Formatting                           ||    [7.6] Random Access File Writing (.dat)");
        System.out.println("    [1.6] Scanner : Console input                                ||    [7.7] Random Access File Reading (.dat)");
        System.out.println("    [1.7] Casting Types                                          ||    [7.8] Object File Writing (Serialization)(.dat)");
        System.out.println("    [1.8] Random()                                               ||    [7.9] Object File Reading (Deserialization)(.dat)");
        System.out.println("    [1.9] Math.* : Static Methods                                || Chapter 8: Exceptions");
        System.out.println("Chapter 2: Expressions, Statements and Methods                   ||    [8.1] Try {} Catch {}");
        System.out.println("    [2.1] Methods                                                ||    [8.2] Throwable Hierarchy");
        System.out.println("    [2.2] While, For & Do                                        ||    [8.3] Crafting Exceptions");
        System.out.println("    [2.3] Switch Statement                                       || Chapter 9: Databases");
        System.out.println("    [2.4] Method Overloading                                     ||    [9.1] Setup Apache Derby");
        System.out.println("    [2.5] Method Example : sumDigits()                           ||");
        System.out.println("Chapter 3: GUI & JavaFX                                          ||");
        System.out.println("    [3.1] Swing Basic Controls                                   ||");
        System.out.println("    [3.2] JavaFX Setup                                           ||");
        System.out.println("    [3.3] JavaFX Basic Controls                                  ||");
        System.out.println("    [3.4] JavaFX SceneBuilder                                    ||");
        System.out.println("Chapter 4: Object Oriented Programming                           ||");
        System.out.println("    [4.1] Classes                                                ||");
        System.out.println("    [4.2] Class Vs Instance Vs Object Vs Reference               ||");
        System.out.println("    [4.3] Encapsulation : Getters & Setters                      ||");
        System.out.println("    [4.4] Constructors                                           ||");
        System.out.println("    [4.5] This Vs Super                                          ||");
        System.out.println("    [4.6] Method Overriding                                      ||");
        System.out.println("    [4.7] Inheritance                                            ||");
        System.out.println("    *[4.8]                                                       ||");
        System.out.println("    [4.9] Access Modifiers                                       ||");
        System.out.println("Chapter 5: Array, Java inbuilt Lists, Auto/Unboxing              ||");
        System.out.println("    [5.1] Array Information                                      ||");
        System.out.println("    [5.2] Array (1D)                                             ||");
        System.out.println("    [5.3] Array (2D)                                             ||");
        System.out.println("    [5.4] Arrays Lists                                           ||");
        System.out.println("    [5.5] Value Vs Reference Semantics                           ||");
        System.out.println("    [5.6] Assigning Objects to Arrays                            ||");
        System.out.println("    [5.7] AutoBoxing                                             ||");
        System.out.println("    [5.8] LinkedList                                             ||");

        //Prompt User Input
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("[Chapter.Section] Enter Index Number = ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
    private static boolean tableOfContents(Scanner input) {
        //PRINT TABLE OF CONTENTS
        printMenu();

        //Set UserNumber if input has double
        double userNumber = 0;
        if (input.hasNextDouble()) {
            userNumber = input.nextDouble();
        }

        //Load that section
        callSection(userNumber);

        //CHECK IF USER WANTS TO LOOP && RETURN VALUE FOR "shouldWeLoop"
        char shouldUserReturn = input.next().charAt(0);

        //Return
        return (Character.toLowerCase(shouldUserReturn) != 'n'); //Exit
    }

    //Call Sections & Insert Title
    private static void callSection(double userNumber) {
        //Load the Title
        loadTitle(userNumber);

        int chapterNumber = (int) userNumber;
        int sectionNumber = (int) ((userNumber * 10) % 10);

        //Try to call method name
        String methodName = "section"+chapterNumber+"_"+sectionNumber;
        boolean success = false;
        try {
            Method m = KuroNote.class.getDeclaredMethod(methodName);
            m.setAccessible(true);
            m.invoke(null);

            success = true;
        }
        catch (NoSuchMethodException e){
            System.out.println(e.getMessage());
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //Otherwise prompt new entry
        if(!success){
            System.out.println("Try choosing a listed number next time...");
        }

        //Escape suggestion
        System.out.println("---------------------------------------------------------------");
        System.out.println("[Any Key] RETURN TO TABLE OF CONTENTS\n" +
                "[N] EXIT");
        System.out.println("---------------------------------------------------------------");
    }
    private static void loadTitle(double userNumber) {
        //Load Title "CHAPTER X, SECTION X : XXX"
        int chapterNumber = (int) userNumber;
        int sectionNumber = (int) ((userNumber * 10) % 10);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("CHAPTER " + chapterNumber + " SECTION " + sectionNumber);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }

    //CHAPTER 0
    private static void section0_0() {
        System.out.println("KuroNote Instructions");
        System.out.println("------------------");
        System.out.println("Hello, Welcome to KuroNote!");
        System.out.println();
        System.out.println("Description: This is a lightning fast Java reference guide!");
        System.out.println();
        System.out.println("Ideal for someone who...");
        System.out.println("    - knows Java");
        System.out.println("        || or ||");
        System.out.println("    - learning Java elsewhere");
        System.out.println("...to get a quick reference to the nuances within Java. ");
        System.out.println();
        System.out.println("Enter numbers in X.Y Format: X=Chapter & Y=Section  ");
        System.out.println();
        System.out.println(" v v Follow the instructions below v v ");
    }
    private static void section0_1() {
        System.out.println("HOT KEYS!");
        System.out.println("-------------Intellij IDE [Favorites]----");
        System.out.println("[Ctrl] + [Shift] + [F10] =                          Run Program");
        System.out.println("[Ctrl] + [Shift] + [A] =                            Find Action");
        System.out.println("[CTRL] + [SHIFT] + [ALT] + [S] =                    Project Structure");
        System.out.println("[CTRL] + [F9] =                                     Rebuild Project");
        System.out.println("[CTRL] + [K] =                                      GitHub Commit Window ");
        System.out.println("[CTRL] + [SHIFT] + [K] =                            GitHub Push Window");
        System.out.println("[CTRL] + [\"[\"] =                                    Go to Beginning of Code Block");
        System.out.println("[CTRL] + [\"]\"] =                                    Go to End of Code Block");
        System.out.println("[Ctrl] + [Shift] + [NumPad+] =                      Expand all");
        System.out.println("[Ctrl] + [Shift] + [NumPad-] =                      Collapse all");
        System.out.println("-------------Eclipse IDE [Favorites]----");
        System.out.println("[Ctrl] + [F11] =                                    Run Program");
        System.out.println("-------------Intellij IDE---------------");
        System.out.println("Double [Shift] =                                    Search Everywhere");
        System.out.println("Double [Ctrl] =                                     Run Anything");
        System.out.println("[Ctrl ] + [/] =                                     Comment/Uncomment a line");
        System.out.println("[Ctrl ] + [C] =                                     Select Entire Line");
        System.out.println("[Ctrl ] + [Shift] + [Left & Right Arrow Keys] =     Move Cursor Quickly Whilst Selecting");
        System.out.println("[Ctrl ] + [Shift] + [Up & Down Arrow Keys] =        Move Current Line Position");
        System.out.println("[Ctrl] + [Delete] =                                 Delete Until Word End");
        System.out.println("[Ctrl] + [Backspace] =                              Delete Until Word Start");
        System.out.println("[\"sout\"] + [ENTER] =                                System.out.println();");
        System.out.println("[F12] =                                             Select Run window");
        System.out.println("[F2] + [Shift] =                                    F2Navigate between code issues");
        System.out.println("[Alt] + [Enter] =                                   Show intention actions and quick-fixes");
        System.out.println("[Ctrl] + [E] =                                      View recent files");
        System.out.println("[Ctrl] + [Shift] + [Enter] =                        Complete current statement");
        System.out.println("[Ctrl] + [Alt] + [L] =                              Reformat code");
        System.out.println("[Ctrl] + [Shift] + [Alt] + [T] =                    Invoke refactoring");
        System.out.println("[Ctrl] + [W] =                                      Extend or shrink selection");
        System.out.println("[Ctrl] + [Shift] + [W] =                            Increase or decrease the scope of selection according to specific code constructs.");
        System.out.println("[Ctrl] + [B] =                                      Go to declaration");
        System.out.println("[Alt] + [F7] =                                      Find usages");
        System.out.println("[Alt] + [1] =                                       Focus the Project tool window");
        System.out.println("[Escape] =                                          Focus the editor");
        System.out.println("-------------Good To Know---------------");
        System.out.println("[Ctrl] + [Shift] + [U] =                            Toggle Upper & Lower Case");
        System.out.println("[Ctrl] + [Shift] + [/] =                            Comment/Uncomment a block of code");
        System.out.println("[Ctrl] + [Y] =                                      To delete a line (Or Redo depending on setting)");
        System.out.println("[SHIFT] + [DELETE] = [Delete behaves as backspace]");
    }
    private static void section0_2() {
        System.out.println("Git & GitHub");
        System.out.println("----------------------------------------");
        System.out.println("GitHub (Intellij)");
        System.out.println("SHORTCUTS");
        System.out.println("   CTRL + K = Commit Window");
        System.out.println("   CTRL + SHIFT + K = Push Window");
        System.out.println("Share a project on GitHub");
        System.out.println("   1.\tOpen the project you want to share.\n" +
                "   2.\tFrom the main menu, choose VCS | Import into Version Control | Share Project on GitHub.\n" +
                "   If you have already registered your GitHub account in IntelliJ IDEA, connection will be established using these credentials.\n" +
                "   If you have not registered your account in IntelliJ IDEA, the Login to GitHub dialog opens. Specify your access token or request a new one with your login and password.\n" +
                "   3.\tWhen connection to GitHub has been established, the Share Project on GitHub dialog opens. Specify the new repository name, the name of the remote, and enter a description of your project.\n" +
                "   You can select the Private option if you do not want to allow public access to your repository for other GitHub users (note that this option is unavailable for free accounts).\n" +
                "   4.\tClick Share to initiate a new repository and upload project sources to it.\n");
    }
    private static void section0_3() {
        System.out.println("CREATE A JAR FILE WITH INTELLIJ IDEA (.jar)");
        System.out.println("----------------------------------------");
        System.out.println("SHORTCUTS");
        System.out.println("   CTRL + SHIFT + ALT + S = Project Structure");
        System.out.println("   CTRL + F9 = Rebuild Project");
        System.out.println("\n");
        System.out.println("STEPS TO CREATE .jar");
        System.out.println("1. Project Structure");
        System.out.println("2. Build Artifact...");
        System.out.println("3. Locate In project (Out/artifacts/ProjectName_jar) \n\t Copy Path");
        System.out.println("4. Open CMD \n\t Example: \"java -jar C:\\intellijCodeRepository\\Kennedy\\out\\artifacts\\Kennedy_jar\\Kennedy.jar\"");
        System.out.println("\nCREATE A BAT FILE (.bat)");
        System.out.println("1. Create Note (.txt)");
        System.out.println("2. Edit and insert \n\t java -jar C:\\intellijCodeRepository\\Kennedy\\out\\artifacts\\Kennedy_jar\\Kennedy.jar");
        System.out.println("3. Rename and change extension to .bat");
        System.out.println("4. CREATE SHORTCUT! =]");
    }
    private static void section0_4() {
        System.out.println("Comments");
        System.out.println("----------------------------------------");
        System.out.println("Where to place comments:");
        System.out.println("    - at the top of each file (a \"comment header\")");
        System.out.println("    - at the start of every method");
        System.out.println("    - to explain complex pieces of code");
        System.out.println("Comments are useful for:");
        System.out.println("    - Understanding larger, more complex programs.");
        System.out.println("    - Multiple programmers working together, who must understand each other's code.");
        System.out.println("---EXAMPLE------------------------------");
        System.out.println("// This is a one-line comment.");
        System.out.println("/* This is a very long \nmulti-line comment. */");
    }
    private static void section0_5() {
        System.out.println("Naming Convention");
        System.out.println("------------------------------------------------------------------");
        System.out.println("PACKAGE NAMES");
        System.out.println("1. Always lower case");
        System.out.println("2. Packages should be unique");
        System.out.println("3. Use your internet domain name, reversed, as a prefix for the package name.");
        System.out.println("4. Can't start with a number");
        System.out.println("        Examples:");
        System.out.println("    A. Switch.suppliers.com -> com.supplier._switch");
        System.out.println("    B. 1world.com -> com._1world");
        System.out.println("    C. Expert-exchange.com -> com.experts_exchange");
        System.out.println("------------------------------------------------------------------");
        System.out.println("CLASS NAMES                        | INTERFACE NAMES");
        System.out.println("1. CamelCase                       | 1. CamelCase");
        System.out.println("2. Must be unique                  | 2. Must be unique");
        System.out.println("3. Can't start with a number       | 3. Can't start with a number");
        System.out.println("        Examples:                  |     Examples:");
        System.out.println("    A. ArrayList                   |     A. List");
        System.out.println("    B. LinkedList                  |     B. Comparable");
        System.out.println("    C. String                      |     C. Serializable");
        System.out.println("    D. TopSong                     |");
        System.out.println("    E. GearBox                     |");
        System.out.println("    F. Main                        |");
        System.out.println("------------------------------------------------------------------");
        System.out.println("METHOD NAMES                       | VARIABLE NAMES");
        System.out.println("1. mixedCase                       |     1. mixedCase");
        System.out.println("2. often verbs                     |     2. meaningful and indicative");
        System.out.println("3. should reflect the function     |     3. Start with lower case letter");
        System.out.println("  preformed or the result returned |     4. do NOT use underscores _");
        System.out.println("        Examples:                  |         Examples:");
        System.out.println("    A. size()                      |     A. positionOfUnit");
        System.out.println("    B. getName()                   |     B. velocityOfUnit");
        System.out.println("    C. addPlayer()                 |     C. accelerationOfUnit");
        System.out.println("------------------------------------------------------------------");
        System.out.println("EXAMPLES                           | CONSTANTS NAMES");
        System.out.println("A name must start with             | 1. ALL UPPER_CASE");
        System.out.println("      a letter, _ or $             | 2. Separate words with an underscore _");
        System.out.println("  Legal:                           | 3. Declared using the \"final\" keyword");
        System.out.println("    masterSword                    | 4. Must be unique");
        System.out.println("    MasterSword                    |         Examples:");
        System.out.println("    MASTER_SWORD_4                 |     A. GRID_SIZE");
        System.out.println("    $masterSword$                  |     B. MAX_INT");
        System.out.println("  Illegal:                         |     C. SEVERITY_ERROR");
        System.out.println("    master+Sword                   |     D. P1 (Used for Pi 3.141592653");
        System.out.println("    4masterSword                   |");
        System.out.println("    master-Sword                   |");
        System.out.println("    master.Sword                   |");
        System.out.println("    masterSword's                  |");
        System.out.println("------------------------------------------------------------------");
    }
    private static void section0_6() {
        System.out.println("Unicode");
        System.out.println("---------------------------------------------------------------");
//        System.out.println("----------Superscripts----------");
//        System.out.println("⁰  \tU+2070 (alt-08304)\tSUPERSCRIPT ZERO\n" +
//                "ⁱ  \tU+2071 (alt-08305)\tSUPERSCRIPT LATIN SMALL LETTER I\n" +
//                "⁴  \tU+2074 (alt-08308)\tSUPERSCRIPT FOUR\n" +
//                "⁵  \tU+2075 (alt-08309)\tSUPERSCRIPT FIVE\n" +
//                "⁶  \tU+2076 (alt-08310)\tSUPERSCRIPT SIX\n" +
//                "⁷  \tU+2077 (alt-08311)\tSUPERSCRIPT SEVEN\n" +
//                "⁸  \tU+2078 (alt-08312)\tSUPERSCRIPT EIGHT\n" +
//                "⁹  \tU+2079 (alt-08313)\tSUPERSCRIPT NINE\n" +
//                "⁺  \tU+207A (alt-08314)\tSUPERSCRIPT PLUS SIGN\n" +
//                "⁻  \tU+207B (alt-08315)\tSUPERSCRIPT MINUS\n" +
//                "⁼  \tU+207C (alt-08316)\tSUPERSCRIPT EQUALS SIGN\n" +
//                "⁽  \tU+207D (alt-08317)\tSUPERSCRIPT LEFT PARENTHESIS\n" +
//                "⁾  \tU+207E (alt-08318)\tSUPERSCRIPT RIGHT PARENTHESIS\n" +
//                "ⁿ  \tU+207F (alt-08319)\tSUPERSCRIPT LATIN SMALL LETTER N\n");
//        System.out.println("----------Subscripts----------");
//        System.out.println("₀  \tU+2080 (alt-08320)\tSUBSCRIPT ZERO\n" +
//                "₁  \tU+2081 (alt-08321)\tSUBSCRIPT ONE\n" +
//                "₂  \tU+2082 (alt-08322)\tSUBSCRIPT TWO\n" +
//                "₃  \tU+2083 (alt-08323)\tSUBSCRIPT THREE\n" +
//                "₄  \tU+2084 (alt-08324)\tSUBSCRIPT FOUR\n" +
//                "₅  \tU+2085 (alt-08325)\tSUBSCRIPT FIVE\n" +
//                "₆  \tU+2086 (alt-08326)\tSUBSCRIPT SIX\n" +
//                "₇  \tU+2087 (alt-08327)\tSUBSCRIPT SEVEN\n" +
//                "₈  \tU+2088 (alt-08328)\tSUBSCRIPT EIGHT\n" +
//                "₉  \tU+2089 (alt-08329)\tSUBSCRIPT NINE\n" +
//                "₊  \tU+208A (alt-08330)\tSUBSCRIPT PLUS SIGN\n" +
//                "₋  \tU+208B (alt-08331)\tSUBSCRIPT MINUS\n" +
//                "₌  \tU+208C (alt-08332)\tSUBSCRIPT EQUALS SIGN\n" +
//                "₍  \tU+208D (alt-08333)\tSUBSCRIPT LEFT PARENTHESIS\n" +
//                "₎  \tU+208E (alt-08334)\tSUBSCRIPT RIGHT PARENTHESIS\n");
        System.out.println("----------<<<EXAMPLE>>>----------");
        System.out.println("public class Main {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        char myChar = 'D';\n" +
                "        char myUnicodeChar = '\\u0044';\n" +
                "\t//Both are just D!\n" +
                "        System.out.println(myChar);\n" +
                "        System.out.println(myUnicodeChar);\n" +
                "\n" +
                "\t//Don't forget use CC!   =]\n" +
                "        char myCopyrightChar = '\\u00A9';\n" +
                "        System.out.println(myCopyrightChar);\n" +
                "    }\n"+
                "}");
        System.out.println("----------Favorites----------");
        System.out.println("φ  \tU+03C6 (alt-0966)\tGREEK SMALL LETTER PHI\n" +
                "∞  \tU+221E (alt-08734)\tINFINITY\n" +
                "Δ  \tU+0394 (alt-0916)\tGREEK CAPITAL LETTER DELTA\n" +
                "Σ  \tU+03A3 (alt-0931)\tGREEK CAPITAL LETTER SIGMA\n" +
                "∫  \tU+222B (alt-08747)\tINTEGRAL\n" +
                "π  \tU+03C0 (alt-0960)\tGREEK SMALL LETTER PI\n" +
                "≅  \tU+2245 (alt-08773)\tAPPROXIMATELY EQUAL TO\n" +
                "≆  \tU+2246 (alt-08774)\tAPPROXIMATELY BUT NOT ACTUALLY EQUAL TO\n" +
                "≇  \tU+2247 (alt-08775)\tNEITHER APPROXIMATELY NOR ACTUALLY EQUAL TO\n" +
                "≈  \tU+2248 (alt-08776)\tALMOST EQUAL TO = asymptotic to\n" +
                "∓  \tU+2213 (alt-08723)\tMINUS-OR-PLUS SIGN\n" +
                "√  \tU+221A (alt-08730)\tSQUARE ROOT = radical sign\n" +
                "∛  \tU+221B (alt-08731)\tCUBE ROOT\n" +
                "∜  \tU+221C (alt-08732)\tFOURTH ROOT\n" +
                "∝  \tU+221D (alt-08733)\tPROPORTIONAL TO\n" +
                "μ  \tU+03BC (alt-0956)\tGREEK SMALL LETTER MU\n" +
                "χ  \tU+03C7 (alt-0967)\tGREEK SMALL LETTER CHI\n" +
                "α  \tU+03B1 (alt-0945)\tGREEK SMALL LETTER ALPHA\n" +
                "β  \tU+03B2 (alt-0946)\tGREEK SMALL LETTER BETA\n" +
                "ω  \tU+03C9 (alt-0969)\tGREEK SMALL LETTER OMEGA\n+" +
                "Ω  \tU+03A9 (alt-0937)\tGREEK CAPITAL LETTER OMEGA\n" +
                "ψ  \tU+03C8 (alt-0968)\tGREEK SMALL LETTER PSI\n" +
                "Ψ  \tU+03A8 (alt-0936)\tGREEK CAPITAL LETTER PSI\n" +
                "∟  \tU+221F (alt-08735)\tRIGHT ANGLE\n" +
                "∠  \tU+2220 (alt-08736)\tANGLE\n" +
                "∡  \tU+2221 (alt-08737)\tMEASURED ANGLE\n" +
                "∢  \tU+2222 (alt-08738)\tSPHERICAL ANGLE = angle arc\n");
    }

    //CHAPTER 1
    private static void section1_1() {
        System.out.println("Primitive Types");
        System.out.println("---------------------------------------------------------------");
        byte myMinByteValue = Byte.MIN_VALUE;
        byte myMaxByteValue = Byte.MAX_VALUE;
        System.out.println("Byte Minimum Value = " + myMinByteValue);
        System.out.println("Byte Maximum Value = " + myMaxByteValue);
        System.out.println("---------------------------------------------------------------");
        short myMinShortValue = Short.MIN_VALUE;
        short myMaxShortValue = Short.MAX_VALUE;
        System.out.println("Short Minimum Value = " + myMinShortValue);
        System.out.println("Short Maximum Value = " + myMaxShortValue);
        System.out.println("---------------------------------------------------------------");
        int myMinIntValue = Integer.MIN_VALUE;
        int myMaxIntValue = Integer.MAX_VALUE;
        System.out.println("Int Minimum Value = " + myMinIntValue);
        System.out.println("Int Maximum Value = " + myMaxIntValue);
        System.out.println("---------------------------------------------------------------");
        long myMinLongValue = Long.MIN_VALUE;
        long myMaxLongValue = Long.MAX_VALUE;
        System.out.println("Long Minimum Value = " + myMinLongValue);
        System.out.println("Long Maximum Value = " + myMaxLongValue);
        System.out.println("---------------------------------------------------------------");
        float myMinFloatValue = Float.MIN_VALUE;
        float myMaxFloatValue = Float.MAX_VALUE;
        System.out.println("Float Minimum Value = " + myMinFloatValue);
        System.out.println("Float Maximum Value = " + myMaxFloatValue);
        System.out.println("---------------------------------------------------------------");
        double myMinDoubleValue = Double.MIN_VALUE;
        double myMaxDoubleValue = Double.MAX_VALUE;
        System.out.println("Double Minimum Value = " + myMinDoubleValue);
        System.out.println("Double Maximum Value = " + myMaxDoubleValue);
        System.out.println("---------------------------------------------------------------");
        System.out.println("Memory :  08 bits = 1 byte");
        System.out.println("---------------------------------------------------------------");
        System.out.println("byte    = 08 bit || 1 byte");
        System.out.println("short   = 16 bit || 2 byte");
        System.out.println("int     = 32 bit || 4 byte");
        System.out.println("long    = 64 bit || 8 byte");
        System.out.println("float   = 32 bit || 4 byte");
        System.out.println("double  = 64 bit || 8 byte");
        System.out.println("boolean = 08 bit || 1 byte");
        System.out.println("char    = 16 bit || 2 byte");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Examples");
        System.out.println("---------------------------------------------------------------");
        System.out.println("char [variableName] =  'A';");
        System.out.println("boolean [variableName] = true;");
        System.out.println("byte [variableName] = 120;");
        System.out.println("short [variableName] = 30_000;");
        System.out.println("int [variableName] = 2_000_000;");
        System.out.println("long [variableName] = 3_000_000L;");
        System.out.println("float [variableName] = 3.14F;");
        System.out.println("double [variableName] = 6.18D; ");
    }
    private static void section1_2() {
        System.out.println("Remainder Operator (%)");
        System.out.println("----------------------------------------");
        System.out.println("Also Known as \"Modulo\"!");
        System.out.println("----------------------------------------");
        System.out.println("Obtain last Digit of a number:");
        System.out.println("    17026 % 10 = 6");
        System.out.println("Obtain last 4 Digits of a number:");
        System.out.println("    17026 % 10000 = 7026");
        System.out.println("Determine if a number is odd:");
        System.out.println("    17026 % 2 = 0   ||   4 % 2 = 0   ||   5 % 2 = 1");
        System.out.println("---------------------------------");
        System.out.println("class Remainder {\n" +
                "\n" +
                "  public static void main (String args[]) {\n" +
                "\n" +
                "    int i = 10;\n" +
                "    int j = 3;\n" +
                "\n" +
                "    System.out.println(\"i is \" + i);\n" +
                "    System.out.println(\"j is \" + j);\n" +
                "  \n" +
                "    int k = i % j;\n" +
                "    System.out.println(\"i%j is \" + k);\n" +
                "    // 10 % 3 = 1\n" +
                "  }\n" +
                "\n" +
                "}");
    }
    private static void section1_3() {
        System.out.println("String: Escape Sequences");
        System.out.println("----------------------------------------");
        System.out.println("For example if I want...");
        System.out.println("----------------------------------------");
        System.out.println("1. String messageA = \"\"Josh\"\";");
        System.out.println("use (\\\") instead of (\") ");
        System.out.println("----------------------------------------");
        System.out.println("2. String messageB = \"c:\\Windows\\...\";");
        System.out.println("use (\\\\) instead of (\\)");
        System.out.println("----------------------------------------");
        System.out.println("3. String messageC = \"First line \n second line\";");
        System.out.println("use (\\n) where you want to split the line");
        System.out.println("----------------------------------------");
        System.out.println("4. String messageD = \"\tTab\";");
        System.out.println("use (\\t) where you want to insert tab");
        System.out.println("----------------------------------------");
    }
    private static void section1_4() {
        System.out.println("String: Methods");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------- return[boolean] equals() ---");
        System.out.println("    String s1=\"javatpoint\";  \n" +
                "    String s2=\"javatpoint\";  ");
        System.out.println("    System.out.println(s1.equals(s2));//true because content and case is same  ");
        System.out.println("--------------------------------------------------------- return[char] charAt(int index) ---");
        System.out.println("    String x = \"airplane\";\n" +
                "    System.out.println( x.charAt(2) ); // output is 'r'");
        System.out.println("-------------------------------------------------------- return[String] concat(String s) ---");
        System.out.println("    String x = \"book\";\n" +
                "    System.out.println( x.concat(\" author\") ); // output is \"book author\"");
        System.out.println("    String x = \"library\";\n" +
                "    System.out.println( x + \" card\"); // output is \"library card\"\n" +
                "    String x = \"United\";\n" +
                "    x += \" States\"\n" +
                "    System.out.println( x ); // output is \"United States\"");
        System.out.println("--------------------------------------------- return[boolean] equalsIgnoreCase(String s) ---");
        System.out.println("    String x = \"Exit\"; \n" +
                "    System.out.println( x.equalsIgnoreCase(\"EXIT\")); // is \"true\" \n" +
                "    System.out.println( x.equalsIgnoreCase(\"tixe\")); // is \"false\" ");
        System.out.println("------------------------------------------------------------------- return[int] length() ---");
        System.out.println("    String x = \"01234567\";\n" +
                "    System.out.println( x.length() ); // returns \"8\"");
        System.out.println("--------------------------------------------- return[String] replace(char old, char new) ---");
        System.out.println("    String x = \"oxoxoxox\";\n" +
                "    System.out.println( x.replace('x', 'X') ); // output is  \"oXoXoXoX\"");
        System.out.println("---- return[String] substring(int begin) || return[String] substring(int begin, int end) ---");
        System.out.println("    String x = \"0123456789\"; // the value of each char is the same as its index!\n" +
                "    System.out.println( x.substring(5) ); // output is \"56789\"\n" +
                "    System.out.println( x.substring(5, 8)); // output is \"567\"");
        System.out.println("----------------------------------------------------------- return[String] toLowerCase() ---");
        System.out.println("    String x = \"A New Java Book\";\n" +
                "    System.out.println( x.toLowerCase() ); // output is \"a new java book\"");
        System.out.println("----------------------------------------------------------- return[String] toUpperCase() ---");
        System.out.println("    String x = \"A New Java Book\";\n" +
                "    System.out.println( x.toUpperCase() ); // output is\"A NEW JAVA BOOK\"");
        System.out.println("------------------------------------------------------------------ return[String] trim() ---");
        System.out.println("    String x = \" hi \";\n" +
                "    System.out.println( x + \"x\" ); // result is\" hi x\"\n" +
                "    System.out.println(x.trim() + \"x\"); // result is \"hix\"");
        System.out.println("------------------------------------------------------- return[( char[] )] toCharArray() ---");
        System.out.println("    String s = \"Java\";\n" +
                "    Char [] arrayChar = s.toCharArray();  //this will produce array of size 4");
        System.out.println("----------------------------------------------- return[boolean] contains(\"searchString\") ---");
        System.out.println("    String x = \"Java is programming language\";\n" +
                "    System.out.println(x.contains(\"Amit\")); // This will print false\n" +
                "    System.out.println(x.contains(\"Java\")); // This will print true");
        System.out.println();

    }
    private static void section1_5() {
        System.out.println("String: Parse && Formatting");
        System.out.println("----------------------------------------");
        System.out.println("    //Parsing");
        System.out.println("String FB_Price_String = \"215.26541354\";");
        System.out.println("double FB_Price_double = Double.parseDouble(FB_Price_String);");
        System.out.println("int FB_Price_Int = (int)FB_Price_double;");
        System.out.println();

        System.out.println("    //Formatting");
        System.out.println("----- Console ( W = 7 , D = 2 ) -----");
        String FB_Price_String = "00215.26541354";
        double FB_Price_double = Double.parseDouble(FB_Price_String);
        int FB_Price_Int = (int)FB_Price_double;

        System.out.println("----- %d = Integer -----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %d\", FB_Price_Int);");
        System.out.printf("The Price Of FB Stock = %d", FB_Price_Int);
        System.out.println();
        System.out.println();
        System.out.println("----- %f = Real Number ----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %f\", FB_Price_double);");
        System.out.printf("The Price Of FB Stock = %f", FB_Price_double);
        System.out.println();
        System.out.println();
        System.out.println("----- %s = String ----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %s\", FB_Price_String);");
        System.out.printf("The Price Of FB Stock = %s", FB_Price_String);
        System.out.println();
        System.out.println();
        System.out.println("----- %Wd = Integer, Right Aligned : W = characters wide -----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %Wd\", FB_Price_Int);");
        System.out.printf("The Price Of FB Stock = %7d", FB_Price_Int);
        System.out.println();
        System.out.println();
        System.out.println("----- %Wd = Integer, Left Aligned : W = characters wide -----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %-Wd\", FB_Price_Int);");
        System.out.printf("The Price Of FB Stock = %-7d", FB_Price_Int);
        System.out.println();
        System.out.println();
        System.out.println("----- %Wd = RealNumber, Right Aligned : W = characters wide -----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %Wf\", FB_Price_double);");
        System.out.printf("The Price Of FB Stock = %7f", FB_Price_double);
        System.out.println();
        System.out.println();
        System.out.println("----- %-Wd = RealNumber, Left Aligned : W = characters wide -----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %-Wf\", FB_Price_double);");
        System.out.printf("The Price Of FB Stock = %-7f", FB_Price_double);
        System.out.println();
        System.out.println();
        System.out.println("----- %.Df = RealNumber, Rounded : D = digits after decimal -----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %.Df\", FB_Price_double);");
        System.out.printf("The Price Of FB Stock = %.2f", FB_Price_double);
        System.out.println();
        System.out.println();
        System.out.println("----- %W.Df = RealNumber, Rounded , Right Aligned : W = Characters Wide , D = digits after decimal -----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %W.Df\", FB_Price_double);");
        System.out.printf("The Price Of FB Stock = %7.2f", FB_Price_double);
        System.out.println();
        System.out.println();
        System.out.println("----- %-W.Df = RealNumber, Rounded , Left Aligned : W = Characters Wide , D = digits after decimal -----");
        System.out.println("System.out.println(\"The Price Of FB Stock = %-W.Df\", FB_Price_double);");
        System.out.printf("The Price Of FB Stock = %-7.2f", FB_Price_double);
        System.out.println();
    }
    private static void section1_6() {
        System.out.println("Scanners");
        System.out.println("----------------------------------------");
        System.out.println("    Use Type: [All Instances]");
        System.out.println("Scanner input = new Scanner(System.in);");
        System.out.println("----------------------------------------");
        System.out.println("    Use Type: [int]");
        System.out.println("System.out.println(\"Enter an int: \");");
        System.out.println("int five =input.nextInt();");
        System.out.println("----------------------------------------");
        System.out.println("    Use Type: [double]");
        System.out.println("System.out.println(\"Enter double: \");");
        System.out.println("double userDouble= input.nextDouble();");
        System.out.println("----------------------------------------");
        System.out.println("    Use Type: [String, One-Word]");
        System.out.println("String userName = input.next();");
        System.out.println("----------------------------------------");
        System.out.println("    Use Type: [String, One-Line]");
        System.out.println("String userPrompt = input.nextLine();");
        System.out.println("----------------------------------------");
        System.out.println("    Use Type: [char]");
        System.out.println("System.out.println(\"Enter a char: \");");
        System.out.println("char letter = input.next().charAt(0);");
        System.out.println("----------------------------------------");
        System.out.println("    Use Type: [All Instances]");
        System.out.println("input.close();");
        System.out.println("----------------------------------------");
        System.out.println("Tips: You can pass the Scanner Object as a parameter like...");
        System.out.println("Statement in Main Method:    int sumOfInputNumbers = calculateSum (input)");
        System.out.println("Utilized Method:             public static int calculateSum (Scanner input) { Do the calculations }");
        System.out.println("----------------------------------------");
    }
    private static void section1_7() {
        System.out.println("Casting");
        System.out.println("----------------------------------------");
        System.out.println("public class MyClass {\n" +
                "  public static void main(String[] args) {\n" +
                "    int myInt = 9;\n" +
                "    double myDouble = myInt; // Automatic casting: int to double\n" +
                "\n" +
                "    System.out.println(myInt);      // Outputs 9\n" +
                "    System.out.println(myDouble);   // Outputs 9.0\n" +
                "  }\n" +
                "}");
        System.out.println("public class MyClass {\n" +
                "  public static void main(String[] args) {\n" +
                "    double myDouble = 9.78;\n" +
                "    int myInt = (int) myDouble; // Manual casting: double to int\n" +
                "\n" +
                "    System.out.println(myDouble);   // Outputs 9.78\n" +
                "    System.out.println(myInt);      // Outputs 9\n" +
                "  }\n" +
                "}");
    }
    private static void section1_8() {
        System.out.println("---------------------------------------- ");
        System.out.println("Math.Random");
        System.out.println("----------------------------------------");
        System.out.println("Random rand = new Random();");
        System.out.println("int n = rand.nextInt(10);   // 0-9");
        System.out.println("int n = rand.nextInt(20) + 1;  // 1-20 inclusive");
        System.out.println(" ");
        System.out.println("int n = name.nextInt(size of range) + min");
        System.out.println(" ");
        System.out.println("      (size of range) = (max - min + 1)");
        System.out.println(" ");
        System.out.println("Examples:");
        System.out.println("A random integer between A and B inclusive:");
        System.out.println("    // B - A + 1 = C");
        System.out.println("    int n = rand.nextInt(C) + A;");
        System.out.println("A random integer between 4 and 10 inclusive:");
        System.out.println("    // 10 - 4 + 1 = 7");
        System.out.println("    int n = rand.nextInt(7) + 4;");
        System.out.println("A random number between 1 and 47 inclusive:");
        System.out.println("    // 47 - 1 + 1 = 7");
        System.out.println("    int n = rand.nextInt(47) + 1;");
        System.out.println("A random number between 23 and 30 inclusive:");
        System.out.println("    // 30 - 23 + 1 = 8");
        System.out.println("    int n = rand.nextInt(8) + 23;");
        System.out.println("A random even number between 4 and 12 inclusive:");
        System.out.println("    int n = rand.nextInt(5) * 2 + 4;");
        System.out.println(" ");
        System.out.println("Method name [ Description ]");
        System.out.println("nextInt() [ returns a random integer ]");
        System.out.println("nextInt(max) [ returns a random integer in the range [0, max)in other words, 0 to max-1 inclusive ]");
        System.out.println("nextDouble() [ returns a random real number in the range [0.0, 1.0) ]");
    }
    private static void section1_9() {
        System.out.println("Math.* : Static Methods");
        System.out.println("----------------------------------------");

        //favorites
        System.out.println("--- Favorites ---");
        System.out.println("Math.sqrt() [square root]");
        System.out.println("\tEx. Math.sqrt(2) returns 1.4142135623730951");
        System.out.println("Math.pow() [power, general exponential]");
        System.out.println("\tEx. Math.pow(3, 4) returns 81.0");
        System.out.println("Math.abs() [absolute value]");
        System.out.println("\tEx. Math.abs(-308) returns 308");
        System.out.println("Math.max() [maximum value]");
        System.out.println("\tEx. Math.max(45, 207) returns 207");
        System.out.println("Math.min() [minimum value]");
        System.out.println("\tEx. Math.min(3.8, 2.75) returns 2.75");

        //rounding
        System.out.println("--- Rounding ---");
        System.out.println("Math.ceil() [ceiling rounds upwards]");
        System.out.println("\tEx. Math.ceil(2.13) returns 3.0 (double)");
        System.out.println("Math.floor() [flooring rounds downward]");
        System.out.println("\tEx. Math.floor(2.93) returns 2.0 (double)");
        System.out.println("Math.round() [rounds to the nearest integer]");
        System.out.println("\tEx. Math.round(2.718) returns 3 (int)");

        //sinusoidal
        System.out.println("--- Sinusoidal ---");
        System.out.println("Math.sin() [sin(angle)= opposite / hypotenuse] [angle in radians]");
        System.out.println("\tEx. Math.sin(0) returns 0.0");
        System.out.println("Math.cos() [cos(angle)= adjacent / hypotenuse] [angle in radians]");
        System.out.println("\tEx. Math.cos(Math.PI) returns -1.0");
        System.out.println("Math.toDegrees() [converts from radians to degrees]");
        System.out.println("\tEx. Math.toDegrees(2*Math.PI) returns 360.0");
        System.out.println("Math.toRadians() [converts from degrees to radians]");
        System.out.println("\tEx. Math.toRadians(360) returns 6.283185307179586");

        //exponents
        System.out.println("--- Exponents ---");
        System.out.println("Math.exp() [exponent base e]");
        System.out.println("\tEx. Math.exp(1) returns 2.718281828459045");
        System.out.println("Math.log() [logarithm base e]");
        System.out.println("\tEx. Math.log(Math.E) returns 1.0");
        System.out.println("Math.log10() [logarithm base 10]");
        System.out.println("\tEx. Math.log10(1000) returns 3.0");
    }

    //CHAPTER 2
    private static void section2_1() {
        System.out.println("Methods");
        System.out.println("----------------------------------------");
        System.out.println("Place statements into a static method if:");
        System.out.println("    - The statements are related structurally, and/or");
        System.out.println("    - The statements are repeated.");
        System.out.println("You should not create static methods for:");
        System.out.println("    - An individual println statement.");
        System.out.println("    - Only blank lines. (Put blank println's in main.)");
        System.out.println("    - Unrelated or weakly related statements.(Consider splitting them into two smaller methods.)\n");
        System.out.println("public class CS210_Example {\n" +
                "    public static void main (String [] args){\n" +
                "        int solution = AddMethod(2, 1);\n" +
                "        multiPrintMethod(solution);\n" +
                "    }\n" +
                "    //AccessModifier Non-AccessModifier ReturnType MethodName (ParameterList)\n" +
                "    public static int AddMethod (int parameterA, int parameterB){\n" +
                "        int returnValue = parameterA + parameterB;\n" +
                "        return returnValue;\n" +
                "    }\n" +
                "    //AccessModifier Non-AccessModifier ReturnType MethodName (ParameterList)\n" +
                "    private static void multiPrintMethod (int solution){\n" +
                "        for(int i=0; i<solution; i++) {\n" +
                "            System.out.println(i+\". \"+solution);\n" +
                "        }\n" +
                "    }\n" +
                "}");
        System.out.println("----------------------------------------");
        System.out.println("Console:");
        System.out.println("0. 3");
        System.out.println("1. 3");
        System.out.println("2. 3");
    }
    private static void section2_2() {
        System.out.println("While & For & Do!");
        System.out.println("----------------------------------------");
        System.out.println("Code:");
        System.out.println("        int count =0;\n" +
                "        while(count != 4) {\n" +
                "            System.out.println(\"[while] count value is = \" + count);\n" +
                "            count++;\n" +
                "        }");
        System.out.println("Console:");
        int count = 0;
        while (count != 4) {
            System.out.println("\t[while] count value is = " + count);
            count++;
        }
        System.out.println("----------------------------------------");
        System.out.println("Code:");
        System.out.println("        for (int i=0; i!=4; i++) {\n" +
                "            System.out.println(\"[for] i value is = \"+ i);\n" +
                "        }");
        System.out.println("Console:");
        for (int i = 0; i != 4; i++) {
            System.out.println("\t[for] i value is = " + i);
        }
        System.out.println("----------------------------------------");
        System.out.println("Code:");
        System.out.println("        int beep = 0;\n" +
                "        do {\n" +
                "            System.out.println(\"[do] beep value was = \"+ beep);\n" +
                "            beep++;\n" +
                "        }\n" +
                "        while (beep !=4);");
        System.out.println("Console:");
        int beep = 0;
        do {
            System.out.println("\t[do] beep value was = " + beep);
            beep++;
        }
        while (beep != 4);
    }
    private static void section2_3() {
        System.out.println("Switch Statement");
        System.out.println("----------------------------------------");
        System.out.println("Rule: The variable used in a switch statement can only be **integers**, convertable integers (byte, short, char), Strings and enums. ");
        System.out.println("\nEX.");
        System.out.println("switch (dayType){\n" +
                "   case 0:\n" +
                "       System.out.println(\"Sunday the \"+day+suffix);\n" +
                "       break;\n" +
                "   case 1:\n" +
                "       System.out.println(\"Monday the \"+day+suffix);\n" +
                "       break;\n" +
                "   case 2:\n" +
                "       System.out.println(\"Tuesday the \"+day+suffix);\n" +
                "       break;\n" +
                "   case 3:\n" +
                "       System.out.println(\"Wednesday the \"+day+suffix);\n" +
                "       break;\n" +
                "   case 4:\n" +
                "       System.out.println(\"Thursday the \"+day+suffix);\n" +
                "       break;\n" +
                "   case 5:\n" +
                "       System.out.println(\"Friday the \"+day+suffix);\n" +
                "       break;\n" +
                "   case 6:\n" +
                "       System.out.println(\"Saturday the \"+day+suffix);\n" +
                "       break;\n" +
                "   default:\n" +
                "       System.out.println(\"Invalid day!\");\n" +
                "       break;\n" +
                "}");
    }
    private static void section2_4() {
        System.out.println("Method Overloading");
        System.out.println("----------------------------------------");
        System.out.println("Case 1:\n" +
                "int exMethod(int a, int b, float c); \n" +
                "int exMethod(int var1, int var2, float var3);\n" +
                "\n" +
                "     Compile time error. Argument lists are exactly same. Both methods are having same number, data types and same sequence of data types in arguments.\n" +
                "\n" +
                "Case 2:\n" +
                "int exMethod(int a, int b);\n" +
                "int exMethod(float var1, float var2);\n" +
                "\n" +
                "     Perfectly fine. Valid case for overloading. Here data types of arguments are different.\n" +
                "\n" +
                "Case 3:\n" +
                "int exMethod(int a, int b); \n" +
                "int exMethod(int num);\n" +
                "\n" +
                "     Perfectly fine. Valid case for overloading. Here number of arguments are different.\n");
        System.out.println("Case 4:\n" +
                "float mymethod(int a, float b); \n" +
                "float mymethod(float var1, int var2);\n" +
                "\n" +
                "     Perfectly fine. Valid case for overloading. Sequence of the data types are different, first method is having (int, float) and second is having (float, int).\n" +
                "\n" +
                "Case 5:\n" +
                "int mymethod(int a, int b); \n" +
                "float mymethod(int var1, int var2);\n" +
                "\n" +
                "     Compile time error. Argument lists are exactly same. Even though return type of methods are different, it is not a valid case. Since return type of method doesn’t matter while overloading a method.\n");
    }
    private static void section2_5() {
        System.out.println("Method Example!");
        System.out.println("----------------------------------------");
        System.out.println("Lets Call the method sumDigits()");
        System.out.println("Enter a 3 Digit Number = ");

        Scanner input14 = new Scanner(System.in);
        int number = input14.nextInt(); // 125
        int ex1 = number % 10; // 125%10 -> 5
        int div1 = number / 10; // 125/10 -> 12
        int ex2 = div1 % 10; // 12%10 -> 2
        int div2 = div1 / 10; // 12/10 -> 1
        int ex3 = div2 % 10; // 1%10 -> 1
        int div3 = div2 / 10; // 1/10 -> 0

        int sumEx1 = ex1 + ex2; // 5+2 = 7
        int sumEx2 = sumEx1 + ex3; //7+1 = 8

        System.out.println("    public static int sumDigits (int number){\n" +
                "        //" + number + " < 10 = false\n" +
                "        if (number < 10) {\n" +
                "            return -1;\n" +
                "        }\n" +
                "        int sum = 0;\n" +
                "        // " + number + " > 0 = true\n" +
                "        // " + div1 + " > 0 = true\n" +
                "        // " + div2 + " > 0 = true\n" +
                "        // " + div3 + " > 0 = false\n" +
                "        while (number > 0) {\n" +
                "                   //extract least significant digit\n" +
                "            int digit = number % 10;\n" +
                "               // " + number + "%10 = " + ex1 + "\n" +
                "               // " + div1 + "%10 = " + ex2 + "\n" +
                "               // " + div2 + "%10 = " + ex3 + "\n" +
                "                   //Add the digit to the sum\n" +
                "            sum += digit;\n" +
                "               // sum+" + ex1 + " = " + ex1 + "\n" +
                "               // sum+" + ex2 + " = " + sumEx1 + "\n" +
                "               // sum+" + ex3 + " = " + sumEx2 + "\n" +
                "                   //drop least significant digit\n" +
                "            number /= 10; //same as... number = number/10;\n" +
                "               //" + number + " / 10 = " + div1 + "\n" +
                "               //" + div1 + " / 10 = " + div2 + "\n" +
                "               //" + div2 + " / 10 = " + div3 + "\n" +
                "        }\n" +
                "        return sum;\n" +
                "    }");
        System.out.println(sumDigits(number));
    }

    //CHAPTER 3
    private static void section3_1() {
        System.out.println("Swing Basic Controls");
        System.out.println("---------------------------------------------------------------");
        System.out.println("import javax.swing.*;\n" +
                "import java.awt.event.*;\n" +
                "\n" +
                "public class KiloConverter extends JFrame {\n" +
                "    private JPanel panel;\n" +
                "    private JTextField kiloTextField;\n" +
                "    private JLabel messageLabel;\n" +
                "    private JButton calcButton;\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        new KiloConverter();\n" +
                "    }\n" +
                "    private KiloConverter() {\n" +
                "        setTitle(\"Kilometer Converter\"); // Set the window title.\n" +
                "        setSize(310, 100); // Size of the window.\n" +
                "        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specify what happens when the close button is clicked.\n" +
                "\n" +
                "        buildPanel(); // Build the panel\n" +
                "\n" +
                "        add(panel); // Add the panel to the frame's content pane.\n" +
                "        setVisible(true); // Display the window.\n" +
                "    }\n" +
                "    private void buildPanel() {\n" +
                "        messageLabel = new JLabel(\"Enter a distance in kilometers\"); // Create a label to display instructions.\n" +
                "        kiloTextField = new JTextField(10); // Create a text field 10 characters wide.\n" +
                "        calcButton = new JButton(\"Calculate\"); // Create a button with the caption \"Calculate\".\n" +
                "\n" +
                "        calcButton.addActionListener(new CalcButtonListener()); // Add an action listener to the button.\n" +
                "\n" +
                "        panel = new JPanel(); // Create a JPanel object and let the panel field reference it && Add components to the panel.\n" +
                "        panel.add(messageLabel);\n" +
                "        panel.add(kiloTextField);\n" +
                "        panel.add(calcButton);\n" +
                "    }\n" +
                "    private class CalcButtonListener implements ActionListener {\n" +
                "        public void actionPerformed(ActionEvent e) {\n" +
                "            String input = kiloTextField.getText();\n" +
                "\n" +
                "            double miles = Double.parseDouble(input) * 0.6214;\n" +
                "\n" +
                "            JOptionPane.showMessageDialog(null, input + \" kilometers is \" + miles + \" miles.\");\n" +
                "        }\n" +
                "    }\n" +
                "}");
    }
    private static void section3_2() {
        System.out.println("JavaFX Setup");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Installation:");
        System.out.println("-Download Intellij");
        System.out.println("-Download SceneBuilder");
        System.out.println("-Download the \"JavaFX SDK\" Online");
        System.out.println("Setup:");
        System.out.println("1. New Project -> JavaFX");
        System.out.println("2. Intellij -> File -> ProjectStructure -> Libraries -> + -> Java -> Locate the \"lib\" folder within the SDK (Ex. A:\\PROGRAMS\\javafx-sdk-11.0.2\\lib)");
        System.out.println("3. Top Right Corner -> Edit Configuration -> VM Options -> Paste the following path with respect to the SDK");
        System.out.println("--module-path\n" +
                "\"A:\\PROGRAMS\\javafx-sdk-15.0.1\\lib\"\n" +
                "--add-modules\n" +
                "javafx.controls,javafx.fxml");
        System.out.println("4. Right Click on \"sample.fxml\" -> Open In SceneBuilder -> Type the path of the SceneBuilder Application (Ex. C:\\Program Files\\SceneBuilder\\SceneBuilder.exe)");
        System.out.println("Enjoy!");
        System.out.println("---------------------------------------------------------------");
    }
    private static void section3_3() {
        System.out.println("JavaFX Basic Controls");
        System.out.println("---------------------------------------------------------------");
        System.out.println("    Techniques Creating Handlers for Controls");
        System.out.println("#1. Writing the definition of an inner class that implements the EventHandler interface. Then, instantiating that class, and registering it with a control.");
        System.out.println("#2. Instantiating an anonymous inner class that implements the EventHandler interface, and registering the object with a control.");
        System.out.println("#3. Using a lambda expression to instantiate an anonymous inner class that implements the EventHandler interface, and registering the object with a control.");
        System.out.println();
        System.out.println("    Advantages of each:");
        System.out.println("#1. Defining an inner class, is advantageous for writing event handlers with a lot of code.");
        System.out.println("#2. Instantiating an anonymous inner class, is advantageous for writing event handlers that have only a small amount of code.");
        System.out.println("#3. Using a lambda expression, is the best approach for writing event handlers that have a small amount of code, as long as the code accesses variables that are effectively final.");
        System.out.println();
        System.out.println("--- EXAMPLE CODE: (Lambda Function Handles the Button Control) ---");
        System.out.println("public class EventDemo extends Application\n" +
                "{\n" +
                "    // Field for the Label control\n" +
                "    private Label myLabel;\n" +
                "\n" +
                "    public static void main(String[] args)\n" +
                "    {\n" +
                "        // Launch the application.\n" +
                "        launch(args);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void start(Stage primaryStage) {\n" +
                "        // Create a Label control.\n" +
                "        myLabel = new Label(\"Click the button to see a message.\");\n" +
                "\n" +
                "        // Create a Button control && Register the event handler.\n" +
                "        Button myButton = new Button(\"Click Me\");\n" +
                "\n" +
                "        //Lambda Function to change text\n" +
                "        myButton.setOnAction(event -> {\n" +
                "            myLabel.setText(\"Thanks for clicking the button!\");\n" +
                "        });\n" +
                "\n" +
                "        // Put the Label and Button in a VBox with 10 pixels of spacing && Set the scene's alignment to center.\n" +
                "        VBox vbox = new VBox(10, myLabel, myButton);\n" +
                "        vbox.setAlignment(Pos.CENTER);\n" +
                "\n" +
                "        // Create a Scene with the VBox as its root node.\n" +
                "        Scene scene = new Scene(vbox, 300, 100);\n" +
                "\n" +
                "        // Add the Scene to the Stage.\n" +
                "        primaryStage.setScene(scene);\n" +
                "        primaryStage.setTitle(\"Button Demo\");\n" +
                "        primaryStage.show();\n" +
                "    }\n" +
                "}");
        System.out.println("--- JavaFX Window will display the label && intractable button ---");

    }
    private static void section3_4() {
        System.out.println("JavaFX SceneBuilder");
        System.out.println("---------------------------------------------------------------");
    }

    //CHAPTER 4
    private static void section4_1() {
        System.out.println("Classes");
        System.out.println("----------------------------------------");
        System.out.println("    Code:");
        System.out.println("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "\t    Car toyota = new Car();\n" +
                "\t    Car acura = new Car();\n" +
                "\t    toyota.setModel(\"4Runner\");\n" +
                "\t    System.out.println(\"Toyota Model is \" + toyota.getModel());\n" +
                "        acura.setModel(\"Integra\");\n" +
                "        System.out.println(\"Acura Model is \" + acura.getModel());\n" +
                "    }\n" +
                "}\n" +
                "public class Car {\n" +
                "    private int doors;\n" +
                "    private int wheels;\n" +
                "    private String model;\n" +
                "    private String engine;\n" +
                "    private String colour;\n" +
                "\n" +
                "    public void setModel(String model) {\n" +
                "        String validModel = model.toLowerCase();\n" +
                "        if(validModel.equals(\"4Runner\") || validModel.equals(\"Tacoma\")) {\n" +
                "            this.model = model;\n" +
                "        } \n" +
                "\t\telse if(validModel.equals(\"Integra\") || validModel.equals(\"RSX\")){\n" +
                "\t\t\tthis.model = model;\n" +
                "\t\t}\n" +
                "\t\telse {\n" +
                "            this.model = \"Unknown\";\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public String getModel() {\n" +
                "        return this.model;\n" +
                "    }\n" +
                "}");
        System.out.println("----------------------------------------");
        System.out.println("    Console:");
        System.out.println("Toyota Model is 4Runner");
        System.out.println("Acura Model is Integra");

//        public class Main {
//            public static void main(String[] args) {
//                Car toyota = new Car();
//                Car acura = new Car();
//                toyota.setModel("4Runner");
//                System.out.println("Toyota Model is " + toyota.getModel());
//                acura.setModel("Integra");
//                System.out.println("Acura Model is " + acura.getModel());
//            }
//        }
//        public class Car {
//            private int doors;
//            private int wheels;
//            private String model;
//            private String engine;
//            private String colour;
//
//            public void setModel(String model) {
//                String validModel = model.toLowerCase();
//                if(validModel.equals("4Runner") || validModel.equals("Tacoma")) {
//                    this.model = model;
//                }
//                else if(validModel.equals("Integra") || validModel.equals("RSX")){
//                    this.model = model;
//                }
//                else {
//                    this.model = "Unknown";
//                }
//            }
//
//            public String getModel() {
//                return this.model;
//            }
//        }
    }
    private static void section4_2() {
        System.out.println("Class Vs Instance Vs Object Vs Reference");
        System.out.println("----------------------------------------");
        System.out.println("Analogy: Building a House...");
        System.out.println("-----------------------------");
        System.out.println("Class = The Blueprints");
        System.out.println("Instance = Each House Built");
        System.out.println("Object = The Actual Houses themselves");
        System.out.println("Reference = The Address of the House");
        System.out.println(" ");
        System.out.println("Class House{");
        System.out.println("    private String Color;");
        System.out.println("    public String getColor(){");
        System.out.println("        return color;");
        System.out.println("    }");
        System.out.println("    public void setColor(String color){");
        System.out.println("        this.color = color;");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("public class Main{");
        System.out.println("    public static void main (String [] args){");
        System.out.println("        House blueHouse = new House(\"blue\");");
        System.out.println("        House blueHouse = new House(\"blue\");");
        System.out.println(" ");
        System.out.println("        System.out.println(blueHouse.getColor()); //prints blue");
        System.out.println("        System.out.println(anotherHouse.getColor()); //prints blue");
        System.out.println(" ");
        System.out.println("        anotherHouse.setColor(\"red\");");
        System.out.println("        System.out.println(blueHouse.getColor()); //prints red");
        System.out.println("        System.out.println(anotherHouse.getColor()); //prints red");
        System.out.println(" ");
        System.out.println("        House greenHouse = new House(\"green\");");
        System.out.println("        anotherHouse = greenHouse;");
        System.out.println(" ");
        System.out.println("        System.out.println(blueHouse.getColor()); //prints red");
        System.out.println("        System.out.println(greenHouse.getColor()); //prints green");
        System.out.println("        System.out.println(anotherHouse.getColor()); //prints green");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("-----------------------------");
        System.out.println("public class Product { \n" +
                "  public int code; //Instance Variable\n" +
                "}");
        System.out.println("public class Product { \n" +
                "  public static int code; //Class Variable \n" +
                "}");
        System.out.println("Instance Variables: These variables belong to the instance of a class, thus an object. ");
        System.out.println("       And every instance of that class (object) has it's own copy of that variable.");
        System.out.println("       Changes made to the variable don't reflect in other instances of that class.");
        System.out.println("Class Variables:  These are also known as static member variables and there's only ");
        System.out.println("       one copy of that variable that is shared with all instances of that class.");
        System.out.println("       If changes are made to that variable, all other instances will see the effect of the changes.");
    }
    private static void section4_3() {
        System.out.println("Encapsulation : Getters & Setters");
        System.out.println("----------------------------------------");
        System.out.println("// Java program to demonstrate encapsulation \n" +
                "public class Encapsulate \n" +
                "{ \n" +
                "\t//Variables\n" +
                "    private String geekName; \n" +
                "    private int geekRoll; \n" +
                "    private int geekAge; \n" +
                "  \n" +
                "\t//Getters\n" +
                "    public int getAge()  \n" +
                "    { \n" +
                "      return geekAge; \n" +
                "    } \n" +
                "    public String getName()  \n" +
                "    { \n" +
                "      return geekName; \n" +
                "    } \n" +
                "    public int getRoll()  \n" +
                "    { \n" +
                "       return geekRoll; \n" +
                "    } \n" +
                "\t\n" +
                "\t//Setters\n" +
                "    public void setAge( int newAge) \n" +
                "    { \n" +
                "      geekAge = newAge; \n" +
                "    } \n" +
                "  \n" +
                "    public void setName(String newName) \n" +
                "    { \n" +
                "      geekName = newName; \n" +
                "    }\n" +
                "    public void setRoll( int newRoll)  \n" +
                "    { \n" +
                "      geekRoll = newRoll; \n" +
                "    } \n" +
                "} ");

    }
    private static void section4_4() {
        System.out.println("Constructors");
        System.out.println("----------------------------------------");
        System.out.println("--- CODE ---");
        System.out.println("class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        typicalObject jackObject = new typicalObject(\"Jack\", 80.5);\n" +
                "        jackObject.speak();\n" +
                "\n" +
                "        typicalObject testDefaultObject = new typicalObject();\n" +
                "        testDefaultObject.speak();\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class typicalObject {\n" +
                "    String name;\n" +
                "    double mass;\n" +
                "\n" +
                "    //Constructor with no Parameters\n" +
                "    public typicalObject(){\n" +
                "        this.name = \"Default_Name\";\n" +
                "        this.mass = -1.00;\n" +
                "    }\n" +
                "\n" +
                "    //Constructor with 2 Parameters\n" +
                "    public typicalObject(String name, double mass) {\n" +
                "        this.name = name;\n" +
                "        this.mass = mass;\n" +
                "    }\n" +
                "\n" +
                "    //Object Method\n" +
                "    public void speak(){\n" +
                "        System.out.println(\"Hello, My name is \"+this.name+\"and I weigh \"+this.mass+\" Lbs!\");\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE ---");
        System.out.println("Hello, My name is Jackand I weigh 80.5 Lbs!\n" +
                "Hello, My name is Default_Nameand I weigh -1.0 Lbs!");


    }
    private static void section4_5() {
        System.out.println("This Vs Super");
        System.out.println("----------------------------------------");
        System.out.println("Descriptions:");
        System.out.println("this() = used to call current class's constructor                     this.XXX = used to reference a member on this particular instance");
        System.out.println("super() = used to call Base class's(Parent class's) constructor       super.XXX = used to reference a member of the base class(Parent class)");

        System.out.println("---------- Both examples ----------");
        System.out.println("class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        new Naruto();\n" +
                "    }\n" +
                "}");
        System.out.println("---------- super() example ----------");
        System.out.println("--- Code ---");
        System.out.println("class Ninja {\n" +
                "    Ninja(){\n" +
                "        System.out.println(\"This object is a Ninja!\");\n" +
                "    }\n" +
                "}\n"+
                "class Naruto extends Ninja{\n" +
                "    Naruto(){\n" +
                "        super();     //Calls Ninja() Constructor && MUST BE FIRST LINE IN THIS CONSTRUCTOR\n" +
                "        System.out.println(\"My name is Naruto Uzumaki\");\n" +
                "    }\n" +
                "}");
        System.out.println("--- Console ---");
        System.out.println("This object is a Ninja!");
        System.out.println("My name is Naruto Uzumaki");
        System.out.println("---------- this() example ----------");
        System.out.println("--- Code ---");
        System.out.println("class Naruto {\n" +
                "    int chakra;\n" +
                "\n" +
                "    Naruto(){\n" +
                "        this(10);     //Calls Naruto(x) Constructor && MUST BE FIRST LINE IN THIS CONSTRUCTOR\n" +
                "        System.out.println(\"A. Naruto Default Constructor\");\n" +
                "        System.out.println(\"Naruto()'s Chakra Lvl = \"+chakra);\n" +
                "    }\n" +
                "    Naruto(int x){\n" +
                "        System.out.println(\"B. Naruto 1 Parameter Constructor\");\n" +
                "        this.chakra = x;     //Accesses This Naruto Objects Chakra && Doesn't have to be the first line\n" +
                "        System.out.println(\"Naruto(x)'s Chakra Lvl = \"+chakra);\n" +
                "    }\n" +
                "}");
        System.out.println("--- Console ---");
        System.out.println("B. Naruto 1 Parameter Constructor");
        System.out.println("Naruto(x)'s Chakra Lvl = 10");
        System.out.println("A. Naruto Default Constructor");
        System.out.println("Naruto()'s Chakra Lvl = 10");
    }
    private static void section4_6() {
        System.out.println("Method Overriding");
        System.out.println("----------------------------------------");
        System.out.println("public class Ninja {\n" +
                "    private String name;\n" +
                "    public Ninja(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "    public void shadowCloneJutsu (){\n" +
                "        System.out.println(\"A Shadow Clone of \"+name+\" appears!\");\n" +
                "    }\n" +
                "    public void greeting(){\n" +
                "        System.out.println(\"Hello, my name is \"+name);\n" +
                "    }\n" +
                "}");
        System.out.println("public class Naruto extends Ninja {\n" +
                "    private String catchPhrase;\n" +
                "    public Naruto(){\n" +
                "        this(\"Naruto\", \"Believe it!\");     //Special This Case\n" +
                "    }\n" +
                "    public Naruto(String name, String catchPhrase) {\n" +
                "        super(name);     //Special Super Case\n" +
                "        this.catchPhrase = catchPhrase;     //Normal This Case\n" +
                "    }\n" +
                "    public void SexyJutsu (){\n" +
                "        super.shadowCloneJutsu();     //Normal Super Case\n" +
                "        System.out.println(\"The Shadow Clone preformed a Sexy Transformation!\");\n" +
                "    }\n" +
                "    @Override\n" +
                "    public void greeting() {\n" +
                "        System.out.println(\"My name is Naruto Uzumaki, \"+catchPhrase);\n" +
                "    }\n" +
                "}");
        System.out.println("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        Ninja sasuke = new Ninja(\"Sasuke\");     sasuke.greeting();     sasuke.shadowCloneJutsu();\n" +
                "        System.out.println(\"*--->>> (-'.')-o X o-('.'-) <<<---*\");\n" +
                "        Naruto naruto = new Naruto();     naruto.greeting();     naruto.SexyJutsu();\n" +
                "    }\n" +
                "}");
        System.out.println("----- CODE: -----");
        System.out.println("Hello, my name is Sasuke\n" +
                "A Shadow Clone of Sasuke appears!\n" +
                "*--->>> (-'.')-o X o-('.'-) <<<---*\n" +
                "My name is Naruto Uzumaki, Believe it!\n" +
                "A Shadow Clone of Naruto appears!\n" +
                "The Shadow Clone preformed a Sexy Transformation!");


    }
    private static void section4_7() {
        System.out.println("Inheritance: This Java program illustrates the concept of inheritance");
        System.out.println("----------------------------------------");
        System.out.println("public class Main  {\n" +
                "    public static void main(String args[])  {\n" +
                "        MountainBike mb = new MountainBike(21, 100, 25);\n" +
                "        System.out.println(mb.toString());\n" +
                "    }\n" +
                "}\n" +
                "class Bicycle  {\n" +
                "    public int gear;\n" +
                "    public int speed;\n" +
                "\n" +
                "    public Bicycle(int gear, int speed) {\n" +
                "        this.gear = gear;\n" +
                "        this.speed = speed;\n" +
                "    }\n" +
                "    // the Bicycle class has three methods\n" +
                "    public void applyBrake(int decrement) {\n" +
                "        speed -= decrement;\n" +
                "    }\n" +
                "    public void speedUp(int increment) {\n" +
                "        speed += increment;\n" +
                "    }\n" +
                "    public String toString()  { // toString() method to print info of Bicycle\n" +
                "        return(\"No of gears are \"+gear+\"\\n\"+\"speed of bicycle is \"+speed);\n" +
                "    }\n" +
                "}\n" +
                "class MountainBike extends Bicycle  {\n" +
                "    public int seatHeight; //MountainBike subclass adds one more field\n" +
                "\n" +
                "    public MountainBike(int gear, int speed, int startHeight) {\n" +
                "        super(gear, speed); // invoking base-class(Bicycle) constructor\n" +
                "        seatHeight = startHeight;\n" +
                "    }\n" +
                "\n" +
                "    public void setHeight(int newValue) { //MountainBike subclass adds one more method\n" +
                "        seatHeight = newValue;\n" +
                "    }\n" +
                "\n" +
                "    @Override // overriding toString() method of Bicycle to print more info\n" +
                "    public String toString() {\n" +
                "        return (super.toString()+\"\\nseat height is \"+seatHeight);\n" +
                "    }\n" +
                "}");
        System.out.println("    Console:\n" +
                "No of gears are 21\n" +
                "speed of bicycle is 100\n" +
                "seat height is 25");

    }
    private static void section4_8() {

    }
    private static void section4_9() {
        System.out.println("Access Modifiers");
        System.out.println("---------------");
        System.out.println();
        System.out.println("Modifiers Access:");
        System.out.println("\t-Members with \"modifiers\" can be Accessed by...");
        System.out.println();

        System.out.println("1.\"private\":");
        System.out.println("\t-Same class");
        System.out.println();

        System.out.println("2.\"       \": (default)");
        System.out.println("\t-Same class");
        System.out.println("\t-Package class");
        System.out.println();

        System.out.println("3.\"protected\":");
        System.out.println("\t-Same class");
        System.out.println("\t-Package class");
        System.out.println("\t-Sub class");
        System.out.println();

        System.out.println("4.\"public\":");
        System.out.println("\t-Same class");
        System.out.println("\t-Package class");
        System.out.println("\t-Sub class");
        System.out.println("\t-Any class");
        System.out.println();

        System.out.println("Modifiers Definitions:");
        System.out.println("1.\"private\" = can only be accessed within the class");
        System.out.println("2.\"       \" = (default) can be accessed by other classes that call this class within the package only");
        System.out.println("3.\"protected\" = acts just like the default modifier, except only when inherited it can be called outside of the package");
        System.out.println("4.\"public\" = can be accessed by all other classes that call this class");
        System.out.println();

        System.out.println("Examples:");
        System.out.println("1. private int age;");
        System.out.println("2. int age;");
        System.out.println("3. protected int age;");
        System.out.println("4. public int age;");
    }

    //CHAPTER 5
    private static void section5_1() {
        System.out.println("Arrays Information");
        System.out.println("----------------------------------------");
        System.out.println("--- Vocabulary ---");
        System.out.println("Array: object that stores many values of the same type.");
        System.out.println("    element: One value in an array.");
        System.out.println("    index: A 0-based integer to access an element from an array.");
        System.out.println("--- Format ---");
        System.out.println("arrayType[] arrayName = new arrayType[length];");
        System.out.println("arrayType[] arrayName = {value, value, … value};");
        System.out.println("Ex. int[] numbers = new int[6]");
        System.out.println("    index's:   0,  1,  2,  3,  4,  5");
        System.out.println("    values's:  0,  0,  0,  0,  0,  0");
        System.out.println("Ex. int[] numbers = {12, 49, -2, 26, 17, -6};");
        System.out.println("    index's:   0,  1,  2,  3,  4,  5");
        System.out.println("    values's: 12, 49, -2, 26,  5, -6");
        System.out.println("--- Methods ---");
        System.out.println(" > MethodName");
        System.out.println("    Description, Syntax: [ Arrays.MethodName(parameters) ]");
        System.out.println(" > binarySearch(arrayName, value)");
        System.out.println("    returns the index of the given value in a sorted array (or < 0 if not found)");
        System.out.println(" > copyOf(arrayName, length)");
        System.out.println("    returns a new copy of an array");
        System.out.println(" > equals(arrayName1, arrayName2)");
        System.out.println("    returns true if the two arrays contain same elements in the same order");
        System.out.println(" > fill(arrayName, value)");
        System.out.println("    sets every element to the given value");
        System.out.println(" > sort(arrayName)");
        System.out.println("    arranges the elements into sorted order");
        System.out.println(" > toString(arrayName)");
        System.out.println("    returns a string representing the array, such as \"[10, 30, -25, 17]\"");
        System.out.println("--- Tips ---");
        System.out.println(" > The length can be any integer expression.");
        System.out.println("    int x = 2 * 3 + 1;");
        System.out.println("    int[] data = new int[x % 5 + 2];");
        System.out.println(" > Arrays.toString(arrayName)");
        System.out.println("    An array does not know how to print itself");
        System.out.println("    Arrays.toString() accepts an array as a parameter and returns a String representation of its elements.");
        System.out.println("    Code:    int[] numbers = {12, 49, -2, 26, 17, -6};");
        System.out.println("             System.out.println(Arrays.toString(numbers));");
        System.out.println("    Console: [12, 49, -2, 26, 17, -6]");
        System.out.println(" > Each element initially gets a \"zero-equivalent\" value.");
        System.out.println("    int -> 0");
        System.out.println("    double -> 0.0");
        System.out.println("    boolean -> false");
        System.out.println("    String -> null");
        System.out.println("    other object -> null (meaning \"No Object\")");
        System.out.println(" > Legal indexes: between 0 and the array's length - 1.");
        System.out.println("    Reading or writing any index outside this range will throw an ArrayIndexOutOfBoundsException.");
        System.out.println(" > You cannot resize an existing array");
        System.out.println(" > You cannot compare arrays with == or equals");
    }
    private static void section5_2() {
        System.out.println("1D Arrays");
        System.out.println("----------------------------------------");
        //Print example code
        System.out.println("---    Code: ---");
        System.out.println("public class Array1D {\n" +
                "    public static void main (String [] args){\n" +
                "        System.out.println(\"Welcome to KuroForest!!!\");\n" +
                "        String inventory[] = {\"Sword\", \"Shield\", \"Shadow-Clone jutsu\"};\n" +
                "        String orkUnit[] = {\"Close-Ranged Grunt\", \"Mid-ranged Grunt\", \"Long-range Grunt\"};\n" +
                "\n" +
                "        System.out.println(\"    Turn 1\");\n" +
                "        System.out.println(\"Your Character is using a \" + inventory[0]);\n" +
                "        System.out.println(\"Your facing a \" + orkUnit[0] + \", \" + orkUnit[1] + \" & \" + orkUnit[2] + \"!\");\n" +
                "        System.out.println(\"    Turn 2\");\n" +
                "        System.out.println(\"Your Character is using a \" + inventory[1]);\n" +
                "        System.out.println(\"Your facing a \" + orkUnit[1] + \", \" + orkUnit[2] + \" & \" + orkUnit[0] + \"!\");\n" +
                "        System.out.println(\"    Turn 3\");\n" +
                "        System.out.println(\"Your Character is using a \" + inventory[2]);\n" +
                "        System.out.println(\"Your facing a \" + orkUnit[2] + \", \" + orkUnit[0] + \" & \" + orkUnit[1] + \"!\");");
        System.out.println("---    Console: ---");
        //Actual example code
        System.out.println("Welcome to KuroForest!!!");
        String[] inventory = {"Sword", "Shield", "Shadow-Clone jutsu"};
        String[] orkUnit = {"Close-Ranged Grunt", "Mid-ranged Grunt", "Long-range Grunt"};

        System.out.println("    Turn 1");
        System.out.println("Your Character is using a " + inventory[0]);
        System.out.println("Your facing a " + orkUnit[0] + ", " + orkUnit[1] + " & " + orkUnit[2] + "!");
        System.out.println("    Turn 2");
        System.out.println("Your Character is using a " + inventory[1]);
        System.out.println("Your facing a " + orkUnit[1] + ", " + orkUnit[2] + " & " + orkUnit[0] + "!");
        System.out.println("    Turn 3");
        System.out.println("Your Character is using a " + inventory[2]);
        System.out.println("Your facing a " + orkUnit[2] + ", " + orkUnit[0] + " & " + orkUnit[1] + "!");
        System.out.println("---    Code: ---");
        System.out.println("public class Arrays {\n" +
                "\t\n" +
                "\tpublic static String[] name = new String[6];\n" +
                "\tpublic static int[] age = new int[6];\n" +
                "\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\t//Initial Variables\n" +
                "\t\tdouble sum=0.0;\n" +
                "\t\tint[] numbers = new int[]{10, 20, 30, 40, -50, 60, -70};  \n" +
                "\t\tdouble minValue = Double.MAX_VALUE;\n" +
                "\t\tdouble maxValue = Double.MIN_VALUE;\n" +
                "\n" +
                "\t\t//Step through the Array\n" +
                "\t\tfor(int i = 0; i < numbers.length; i++) {\n" +
                "\t\t\tdouble value = numbers[i];\n" +
                "\t\t\tsum += value;\n" +
                "\t\t\t\n" +
                "\t\t\t//Update Min/Max\n" +
                "\t\t\tif(value < minValue) {\n" +
                "\t\t\t\tminValue = value;\n" +
                "\t\t\t}\n" +
                "\t\t\tif(value > maxValue) {\n" +
                "\t\t\t\tmaxValue = value;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t}\n" +
                "\t\tdouble average = sum/numbers.length;\n" +
                "\t\t\n" +
                "\t\t//Print Results\n" +
                "\t\tSystem.out.println(\"Average value of the array elements is : \" + average);\n" +
                "\t\tSystem.out.println(\"Min Value = \" + minValue);\n" +
                "\t\tSystem.out.println(\"Max Value = \" + maxValue);\t\n" +
                "\t}\n" +
                "}");
        System.out.println("---    Console: ---");
        System.out.println("Average value of the array elements is : 5.714285714285714\n" +
                "Min Value = -70.0\n" +
                "Max Value = 60.0\n");


        //Alternative Method
//        System.out.println("1D Arrays");
//        System.out.println("----------------------------------------");
//        System.out.println("Rules: ");
//        System.out.println(" ");
//        System.out.println("import java.util.Scanner;\n" +
//                "public class array {\n" +
//                "       //Collect Input\n" +
//                "    private static Scanner scanner = new Scanner(System.in);\n" +
//                "\n" +
//                "       //Get Input, Create Array & Get Average\n" +
//                "    public static void main(String[] args) {\n" +
//                "        int[] myIntegers = getIntegers(5);\n" +
//                "        for(int i=0; i<myIntegers.length; i++) {\n" +
//                "            System.out.println(\"Element \" + i +\", typed value was \" + myIntegers[i]);\n" +
//                "        }\n" +
//                "        System.out.println(\"The average is \" + getAverage(myIntegers));\n" +
//                "    }\n" +
//                "       //Create Int Array\n" +
//                "    public static int[] getIntegers(int number) {\n" +
//                "        System.out.println(\"Enter \" + number + \" integer values.\\r\");\n" +
//                "        int[] values = new int[number];\n" +
//                "        for(int i=0; i<values.length; i++) {\n" +
//                "            values[i] = scanner.nextInt();\n" +
//                "        }\n" +
//                "        return values;\n" +
//                "    }\n" +
//                "       //Get Average\n" +
//                "    public static double getAverage(int[] array) {\n" +
//                "        int sum = 0;\n" +
//                "        for(int i=0; i< array.length; i++) {\n" +
//                "            sum += array[i];\n" +
//                "        }\n" +
//                "        return (double) sum / (double)array.length;\n" +
//                "    }\n" +
//                "}\n");
//        System.out.println("       //Create String Array\n" +
//                "    String[] myMentorStocks= { \"CCL\", \"CRUS\", \"ELF\", \"FB\", \"FIZZ\", \"FL\", \"IRBT\", \"RVLV\", \"SWKS\", \"TSLA\", \"UBER\", \"WYNN\"};\n");
//        System.out.println("    printStockArray(myMentorStocks);\n");
//        System.out.println("    public static void printStockArray (String[] myStocks)throws IOException{\n" +
//                "        for (int i = 0; i!=myStocks.length; i++){\n" +
//                "            System.out.print(myStocks[i]);\n" +
//                "            System.out.println(\" = [$\"+fetchPrice(myStocks[i])+\"]\");\n" +
//                "        }\n" +
//                "        System.out.println(\"Total Favorite Stocks: \"+myStocks.length+\"\\n---------------------------------------------------------------------------------\");\n" +
//                "    }");
    }
    private static void section5_3() {
        System.out.println("2D Arrays");
        System.out.println("----------------------------------------");
        System.out.println("    Code:");
        System.out.println("public class TwoDimArrays {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tint[][] lotteryCard = { { 20, 15, 7 }, { 8, 7, 19 }, { 7, 13, 41 } };\n" +
                "\t\tint[][] lotteryCard2 = new int[3][3];\n" +
                "\t\t\n" +
                "\t\tlotteryCard2[0][0] = 20;\n" +
                "\t\tlotteryCard2[0][1] = 15;\n" +
                "\t\tlotteryCard2[0][2] = 7;\n" +
                "\t\tlotteryCard2[1][0] = 8;\n" +
                "\t\tlotteryCard2[1][1] = 7;\n" +
                "\t\tlotteryCard2[1][2] = 19;\n" +
                "\t\tlotteryCard2[2][0] = 7;\n" +
                "\t\tlotteryCard2[2][1] = 13;\n" +
                "\t\tlotteryCard2[2][1] = 41;\n" +
                "\n" +
                "\t\t// [row][column]\n" +
                "\t\tSystem.out.println(lotteryCard[0][0]);\n" +
                "\t\tSystem.out.println(\"---------\");\n" +
                "\t\tfor (int i = 0; i <= 2; i++) {\n" +
                "\t\t\tSystem.out.println(lotteryCard[i][i]);\n" +
                "\t\t}\n" +
                "\t\tSystem.out.println(\"---------\");\n" +
                "\t\tfor (int i = 0; i <= 2; i++) {\n" +
                "\t\t\tfor (int j = 0; j <= 2; j++) {\n" +
                "\t\t\t\tSystem.out.println(lotteryCard[i][j]);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}");
        System.out.println("----------------------------------------");
        System.out.println("    Console:");


        int[][] lotteryCard = {{20, 15, 7}, {8, 7, 19}, {7, 13, 41}};
//        int[][] lotteryCard2 = new int[3][3];
//
//        lotteryCard2[0][0] = 20;
//        lotteryCard2[0][1] = 15;
//        lotteryCard2[0][2] = 7;
//        lotteryCard2[1][0] = 8;
//        lotteryCard2[1][1] = 7;
//        lotteryCard2[1][2] = 19;
//        lotteryCard2[2][0] = 7;
//        lotteryCard2[2][1] = 13;
//        lotteryCard2[2][1] = 41;

        // [row][column]
        System.out.println(lotteryCard[0][0]);
        System.out.println("---------");
        for (int i = 0; i <= 2; i++) {
            System.out.println(lotteryCard[i][i]);
        }
        System.out.println("---------");
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                System.out.println(lotteryCard[i][j]);
            }
        }

    }
    private static void section5_4() {
        System.out.println("Arrays Lists");
        System.out.println("----------------------------------------");
        System.out.println("ArrayList<Integer> numbersList = new ArrayList<>();   //Integers Only");
        System.out.println("ArrayList<String> stringList = new ArrayList<>(); //String Only");
        System.out.println("ArrayList<Object> objectList = new ArrayList<>(); //All Types");
        System.out.println();
        System.out.println("--- METHODS --- Ex. ArrayList<Integer> myArrayList = new ArrayList<>();");
        System.out.println("myArrayList.add(value)        //Add Element to the end of the list");
        System.out.println("myArrayList.add(index, value) //Add Element to the index and shift all Elements beyond that index (Add one to index's)");
        System.out.println("myArrayList.remove(index)     //Remove Element from the index and shift all Elements beyond that index (Subtract one from index's)");
        System.out.println();
        System.out.println("--- EXAMPLE ---");
        System.out.println("ArrayList<Object> myArrayList = new ArrayList<>();\n" +
                "\n" +
                "myArrayList.add(\"A\"); //myArrayList.get(0) = \"A\"\n" +
                "myArrayList.add(\"B\"); //myArrayList.get(1) = \"A\"\n" +
                "myArrayList.add(\"C\"); //myArrayList.get(2) = \"A\"\n" +
                "for (int i = 0; i < myArrayList.size(); i++){         //0 A\n" +
                "    System.out.println(i+\" \"+myArrayList.get(i));     //1 B\n" +
                "}                                                     //2 C\n" +
                "\n" +
                "myArrayList.remove(1);\n" +
                "for (int i = 0; i < myArrayList.size(); i++){         //0 A\n" +
                "    System.out.println(i+\" \"+myArrayList.get(i));     //1 C\n" +
                "}");

    }
    private static void section5_5() {
        System.out.println("Value Vs Reference Semantics");
        System.out.println("----------------------------------------");
        System.out.println("Value Semantics: Behavior where values are copied when assigned, passed as parameters, or returned.");
        System.out.println("   All primitive types in Java use value semantics.\n" +
                "   When one variable is assigned to another, its value is copied.\n" +
                "   Modifying the value of one variable does not affect others.\n");
        System.out.println("EX:     int x = 5;\n" +
                "\tint y = x;     // x = 5, y = 5\n" +
                "\ty = 17;        // x = 5, y = 17\n" +
                "\tx = 8;         // x = 8, y = 17\n");
        System.out.println("Reference semantics: Behavior where variables actually store the address of an object in memory.");
        System.out.println("   When one variable is assigned to another, the object is not copied; both variables refer to the same object.\n" +
                "   Modifying the value of one variable will affect others.\n");
        System.out.println("EX:     int[] a1 = {4, 15, 8};\n" +
                "\tint[] a2 = a1;          // refer to same array as a1\n" +
                "\ta2[0] = 7;\n" +
                "\tSystem.out.println(Arrays.toString(a1)); // [7, 15, 8]\n");
        System.out.println("EX:     public static void main(String[] args) {\n" +
                "\t    int[] iq = {126, 167, 95};\n" +
                "\t    increase(iq);\n" +
                "\t    System.out.println(Arrays.toString(iq));\n" +
                "\t}\n" +
                "\n" +
                "\tpublic static void increase(int[] a) {\n" +
                "\t    for (int i = 0; i < a.length; i++) {\n" +
                "\t        a[i] = a[i] * 2;\n" +
                "\t    }\n" +
                "\t} \n//CONSOLE:[252, 334, 190]");
    }
    private static void section5_6() {
        System.out.println("Assigning Objects to Arrays");
        System.out.println("----------------------------------------");
        System.out.println("--- CODE: ---");
        System.out.println("class unit {\n" +
                "    String name;\n" +
                "    public int atk;\n" +
                "    int def;\n" +
                "\n" +
                "    public unit(String name, int atk, int def) {\n" +
                "        this.name = name;\n" +
                "        this.atk = atk;\n" +
                "        this.def = def;\n" +
                "    }\n" +
                "}\n" +
                "public class ExampleMain {\n" +
                "    public static void main (String [] args){\n" +
                "        unit[] myUnitArray = new unit[3];\n" +
                "        for (int i = 0; i < myUnitArray.length; i++){\n" +
                "            unit Test = new unit(\"Kuro#\"+i, i*2, i+2);\n" +
                "            myUnitArray[i] = Test;\n" +
                "            System.out.println(\"Unit Name = \"+myUnitArray[i].name);\n" +
                "            System.out.println(\"Unit ATK = \"+myUnitArray[i].atk);\n" +
                "            System.out.println(\"Unit DEF = \"+myUnitArray[i].def);\n" +
                "        }\n" +
                "    }\n" +
                "}\n");
        System.out.println("--- CONSOLE: ---");
        System.out.println("Unit Name = Kuro#0\n" +
                "Unit ATK = 0\n" +
                "Unit DEF = 2\n" +
                "Unit Name = Kuro#1\n" +
                "Unit ATK = 2\n" +
                "Unit DEF = 3\n" +
                "Unit Name = Kuro#2\n" +
                "Unit ATK = 4\n" +
                "Unit DEF = 4");
    }
    private static void section5_7(){
        System.out.println("AutoBoxing and UnBoxing");
        System.out.println("----------------------------------------");
        System.out.println("Auto Boxing is using classes to contain data types within a class");
        System.out.println("Just like we would use a wrapper to contain a primitive type");
        System.out.println();
        System.out.println("\t[Below, Class \"Integer\" is a wrapper for the primitive type \"int\"]");
        System.out.println("--- CODE A ---");
        System.out.println("ArrayList<Integer> intArrayList = new ArrayList<Integer>();");
        System.out.println("for (int i = 0; i <= 5; i++){\n" +
                "   intArrayList.add(Integer.valueOf(i)); //AutoBoxing: Converting an int to Integer\n" +
                "}");
        System.out.println("for(int i = 0; i <= 5; i++){\n" +
                "   System.out.println(i + \" --> \" + intArrayList.get(i).intValue()); //UnBoxing: Converting an Integer to int\n" +
                "}");
        //Print to Console
        System.out.println("--- CONSOLE A ---");
        ArrayList<Integer> intArrayList = new ArrayList<>();
        for (int i = 0; i <= 5; i++){
            intArrayList.add(i);
        }
        for(int i = 0; i <= 5; i++){
            System.out.println(i + " --> " + intArrayList.get(i));
        }
        System.out.println("\t[Java will compile a simplified version for the primitive types with their corresponding wrappers");
        System.out.println("--- CODE B ---");
        System.out.println("Integer myIntValue = 56; // Java compiler reads : Integer myIntValue = Integer.valueOf(56);\n" +
                "int myInt = myIntValue; // Java compiler reads :  int myInt = myIntValue.intValue();");
        int myInt = 56; // Java compiler reads :  int myInt = myIntValue.intValue();

        System.out.println("\t[Below, Class \"Double\" is a wrapper for the primitive type \"double\", with the simplified syntax]");
        System.out.println("--- CODE C ---");
        System.out.println("ArrayList<Double>myDoubleValues = new ArrayList<Double>();\n" +
                "for(double dbl = 0.0; dbl <= 5.0; dbl += 0.5){\n" +
                "   myDoubleValues.add(dbl); //AutoBoxing : Instead of Double.valueOf(dbl) we use just dbl\n" +
                "}\n" +
                "for(int i = 0; i < myDoubleValues.size(); i++){\n" +
                "   double value = myDoubleValues.get(i); //UnBoxing : Instead of myDoubleValues.get(i).doubleValue() we use myDoubleValues.get(i)\n" +
                "   System.out.println(i + \" --> \" + value);\n" +
                "}");
        System.out.println("--- CONSOLE C ---");
        ArrayList<Double>myDoubleValues = new ArrayList<>();
        for(double dbl = 0.0; dbl <= 5.0; dbl += 0.5){
            myDoubleValues.add(dbl); //AutoBoxing : Instead of Double.valueOf(dbl) we use just dbl
        }
        for(int i = 0; i < myDoubleValues.size(); i++){
            double value = myDoubleValues.get(i); //UnBoxing : Instead of myDoubleValues.get(i).doubleValue() we use myDoubleValues.get(i)
            System.out.println(i + " --> " + value);
        }
    }
    private static void section5_8(){
        System.out.println("Linked List");
        System.out.println("----------------------------------------");
        System.out.println("--- WHEN TO USE: ---");
        System.out.println("It is best to use an ArrayList when:");
        System.out.println("    -You want to access random items frequently");
        System.out.println("    -You only need to add or remove elements at the end of the list");
        System.out.println("It is best to use a LinkedList when:");
        System.out.println("    -You only use the list by looping through it instead of accessing random items");
        System.out.println("    -You frequently need to add and remove items from the beginning, middle or end of the list");
        System.out.println();
        System.out.println("--- METHODS: ---");
        System.out.println("addFirst()    = Adds an item to the beginning of the list");
        System.out.println("addLast()     = Add an item to the end of the list");
        System.out.println("removeFirst() = Remove an item from the beginning of the list");
        System.out.println("removeLast()  = Remove an item from the end of the list");
        System.out.println("getFirst()    = Get the item at the beginning of the list");
        System.out.println("getLast()     = Get the item at the end of the list");
        System.out.println("*Many ArrayList Methods are functional with a LinkedList");
        System.out.println();
        System.out.println("--- CODE: ---");
        System.out.println("import java.util.LinkedList;\n" +
                "\n" +
                "public class spellManager {\n" +
                "    public static void main(String[] args) {\n" +
                "        LinkedList<String> MagicSpells = new LinkedList<>();\n" +
                "\n" +
                "        //Cast Spells On Stack\n" +
                "        MagicSpells.addFirst(\"1. P1 Used \\\"Chain Lightning\\\" on P2 (Instant)\");\n" +
                "        MagicSpells.addFirst(\"2. P2 Used \\\"Counter\\\" on \\\"Chain Lightning\\\" (Instant)\");\n" +
                "        MagicSpells.addLast(\"3. P3 Used \\\"FireBall\\\" on P2 (Sorcery)\");\n" +
                "        MagicSpells.addFirst(\"4. P2 Used \\\"Counter\\\" on \\\"Fireball\\\" (Instant)\");\n" +
                "\n" +
                "        //Trigger All Spells Off Stack\n" +
                "        int originalLinkedListSize = MagicSpells.size();\n" +
                "        for (int i = 0; i < originalLinkedListSize; i++) {\n" +
                "            System.out.println(\"Spell #\"+(i+1)+\": \"+MagicSpells.getFirst());\n" +
                "            MagicSpells.removeFirst();\n" +
                "        }\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE: ---");
        System.out.println("Spell #1: 4. P2 Used \"Counter\" on \"Fireball\" (Instant)\n" +
                "Spell #2: 2. P2 Used \"Counter\" on \"Chain Lightning\" (Instant)\n" +
                "Spell #3: 1. P1 Used \"Chain Lightning\" on P2 (Instant)\n" +
                "Spell #4: 3. P3 Used \"FireBall\" on P2 (Sorcery)");
    }

    //CHAPTER 6
    private static void section6_1() {
        System.out.println("Enum's in Java");
        System.out.println("----------------------------------------");
        System.out.println("--- CODE: ---");
        System.out.println("enum Warmth {\n" +
                "    LOW,\n" +
                "    MEDIUM,\n" +
                "    HIGH\n" +
                "}\n" +
                "\n" +
                "public class EnumExample {\n" +
                "    public static void main(String[] args) {\n" +
                "        Warmth temp; //Initialize Var \"temp\" with Type: enum Warmth\n" +
                "\n" +
                "        temp = Warmth.LOW; //Assign \"temp\" with value LOW\n" +
                "        printHeatTemp(temp);\n" +
                "\n" +
                "        temp = Warmth.MEDIUM; //Assign \"temp\" with value MEDIUM\n" +
                "        printHeatTemp(temp);\n" +
                "\n" +
                "        temp = Warmth.HIGH; //Assign \"temp\" with value HIGH\n" +
                "        printHeatTemp(temp);\n" +
                "    }\n" +
                "\n" +
                "    private static void printHeatTemp(Warmth _temp){\n" +
                "        switch (_temp){\n" +
                "            case LOW:\n" +
                "                System.out.println(\"Heat is LOW, Turn up the heat!\");\n" +
                "                break;\n" +
                "            case MEDIUM:\n" +
                "                System.out.println(\"Heat is MEDIUM, That's just right!\");\n" +
                "                break;\n" +
                "            case HIGH:\n" +
                "                System.out.println(\"Heat is HIGH, Turn down to avoid system failure!\");\n" +
                "        }\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE: ---");
        System.out.println("Heat is LOW, Turn up the heat!\n" +
                "Heat is MEDIUM, That's just right!\n" +
                "Heat is HIGH, Turn down to avoid system failure!");
    }
    private static void section6_2() {
        System.out.println("Abstract Methods & Classes");
        System.out.println("----------------------------------------");
        System.out.println("An Abstract Method is a method that appears in a superclass, but expects to be overridden within a subclass");
        System.out.println("    - When a class contains an abstract method, you cannot create an instance of the class");
        System.out.println("    - If the sub-class should not override an abstract method from the super-class, then an error will result");
        System.out.println();
        System.out.println("An Abstract Class is not instantiated, but other classes extend it");
        System.out.println("    - Must be a super-class");
        System.out.println("    - An abstract class may have static fields and static methods");
        System.out.println();
        System.out.println("--- EXAMPLE: ---");
        System.out.println();
        System.out.println("abstract class GraphicObject {\n" +
                "    int x, y;\n" +
                "    ...\n" +
                "    void moveTo(int newX, int newY) {\n" +
                "        ...\n" +
                "    }\n" +
                "    abstract void draw();\n" +
                "    abstract void resize();\n" +
                "}");
        System.out.println("class Circle extends GraphicObject {\n" +
                "    void draw() {\n" +
                "        ...\n" +
                "    }\n" +
                "    void resize() {\n" +
                "        ...\n" +
                "    }\n" +
                "}\n" +
                "class Rectangle extends GraphicObject {\n" +
                "    void draw() {\n" +
                "        ...\n" +
                "    }\n" +
                "    void resize() {\n" +
                "        ...\n" +
                "    }\n" +
                "}");
        System.out.println();
    }
    private static void section6_3() {
        System.out.println("Interfaces");
        System.out.println("----------------------------------------");
        System.out.println("In essence, an interface is like a class that contains only abstract methods.");
        System.out.println();
        System.out.println("- Interfaces cannot be instantiated, instead a class must implement the interface & override all methods specified in the interface.");
        System.out.println("- All methods in an interface are implicitly public access by default.");
        System.out.println("- Classes that impliment an interface must have matching signatures (return type & parameters) to all methods within the interface.");
        System.out.println("- If an interface has fields, then they are treated as final and static.");
        System.out.println("- \"default\" is keyword that can allow you define a default method within the interface.");
        System.out.println();
        System.out.println("--- CODE: ---");
        System.out.println("public interface Spells {\n" +
                "    void specialAttack();\n" +
                "\n" +
                "    default void FireBall(){\n" +
                "        System.out.println(\"A FireBall is Launched into the air!\");\n" +
                "    }\n" +
                "}");
        System.out.println();
        System.out.println("public class Wizard implements Spells {\n" +
                "    private String name;\n" +
                "\n" +
                "    public Wizard(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public void specialAttack(){\n" +
                "        System.out.println(name+\" used Abra Kadabra!\");\n" +
                "    }\n" +
                "\n" +
                "}");
        System.out.println("public class Hogwarts {\n" +
                "    public static void main(String[] args) {\n" +
                "        Wizard harry = new Wizard(\"Harry\");\n" +
                "        \n" +
                "        harry.specialAttack();\n" +
                "        harry.FireBall();\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE: ---");
        System.out.println("Harry used Abra Kadabra!\n" +
                "A FireBall is Launched into the air!");
    }
    private static void section6_4() {
        System.out.println("Functional Interfaces & Anonymous Inner Class");
        System.out.println("----------------------------------------");
        System.out.println();
        System.out.println("A Functional Interface is simply an interface that has one abstract method.");
        System.out.println();
        System.out.println("An Anonymous Inner Class enables you to make your code more concise.");
        System.out.println("    -They enable you to declare and instantiate a class at the same time");
        System.out.println("    -They are like local classes except that they do not have a name");
        System.out.println("    -Use them if you need to use a local class only once");
        System.out.println();
        System.out.println("--- CODE: ---");
        System.out.println();
        System.out.println("// Functional Interface : A single calculate method we can implement elsewhere");
        System.out.println("public interface IntCalculator {\n" +
                "    int calculate(int num);\n" +
                "}\n");
        System.out.println("public class AnonymousClassDemo\n" +
                "{\n" +
                "    public static void main(String[] args)\n" +
                "    {\n" +
                "        int num = 5;\n" +
                "\n" +
                "        // Anonymous Inner Class: Create an object that implements IntCalculator\n" +
                "        IntCalculator square = new IntCalculator() {\n" +
                "            public int calculate(int number)\n" +
                "            {\n" +
                "                return number * number;\n" +
                "            }\n" +
                "        };\n" +
                "\n" +
                "        System.out.println(\"The square is \" + square.calculate(num));\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE: ---");
        System.out.println("The square is 25");
    }
    private static void section6_5() {
        System.out.println("Lambda Expressions");
        System.out.println("----------------------------------------");
        System.out.println("Lambda expressions basically express instances of functional interfaces.");
        System.out.println("    - A lambda expression is a short block of code which takes in parameters and returns a value");
        System.out.println("    - Lambda expressions are similar to methods, but they do not need a name and they can be implemented right in the body of a method");
        System.out.println("    - The simplest lambda expression contains a single parameter and an expression:");
        System.out.println("            parameter -> expression");
        System.out.println("    - To use more than one parameter, wrap them in parentheses:");
        System.out.println("            (parameter1, parameter2) -> expression");
        System.out.println("    - Expressions are limited. They have to immediately return a value, and they cannot contain variables, assignments or statements such as if or for.");
        System.out.println("    - If the lambda expression needs to return a value, then the code block should have a return statement.");
        System.out.println("    - In order to do more complex operations, a code block can be used with curly braces:");
        System.out.println("            (parameter1, parameter2) -> { code block }");
        System.out.println();
        System.out.println("--- CODE: ---");
        System.out.println("// Functional Interface : A single calculate method we can implement elsewhere");
        System.out.println("public interface IntCalculator {\n" +
                "    int calculate(int num);\n" +
                "}");
        System.out.println("public class LambdaDemo {\n" +
                "    public static void main(String[] args)\n" +
                "    {\n" +
                "        int num = 5;\n" +
                "\n" +
                "        // Anonymous Inner Class: Create an object that implements IntCalculator\n" +
                "//        IntCalculator square = new IntCalculator() {\n" +
                "//            public int calculate(int number)\n" +
                "//            {\n" +
                "//                return number * number;\n" +
                "//            }\n" +
                "//        };\n" +
                "\n" +
                "        // Lambda Expression: Create an object that implements IntCalculator.\n" +
                "        IntCalculator square = x -> x * x;\n" +
                "\n" +
                "        System.out.println(\"The square is \" + square.calculate(num));\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE: ---");
        System.out.println("The square is 25");
        System.out.println("--- CODE: ---");
        System.out.println("        ArrayList<Integer> numbers = new ArrayList<Integer>();\n" +
                "        numbers.add(3);\n" +
                "        numbers.add(10);\n" +
                "        numbers.add(26);\n" +
                "        numbers.forEach( (n) -> { System.out.println(n); } );");
        System.out.println("--- CONSOLE: ---");
        System.out.println("3\n" +
                "10\n" +
                "26");
    }
    private static void section6_6() {
        System.out.println("Keyword: instanceof");
        System.out.println("----------------------------------------");
        System.out.println("The java \"instanceof\" operator is used to test whether the object is an instance of the specified type (class or subclass or interface).");
        System.out.println("    - Considered as a bad practice");
        System.out.println("            While there is nothing wrong in it and may be required at certain times, but the good design would avoid having to use this keyword");
        System.out.println("    - It is also known as type comparison operator because it compares the instance with type");
        System.out.println("    - It returns either true or false");
        System.out.println("    - If we apply this operator with any variable that has null value, it returns false");
        System.out.println();
        System.out.println("--- GOOD CODE: ---                                      || --- BAD CODE: ---\n" +
                "class Animal {                                          || class Animal {}\n" +
                "    void move(){                                        || class Fish extends Animal {\n" +
                "        System.out.println(\"Move\");                     ||     void swim(){\n" +
                "    }                                                   ||         System.out.println(\"Swim\");\n" +
                "}                                                       ||     }\n" +
                "class Fish extends Animal {                             || }\n" +
                "    @Override void move(){                              || class Bird extends Animal {\n" +
                "        System.out.println(\"Swim\");                     ||     void fly(){\n" +
                "    }                                                   ||         System.out.println(\"Fly\");\n" +
                "}                                                       ||     }\n" +
                "class Bird extends Animal {                             || }\n" +
                "    @Override void move(){                              || class Kangaroo extends Animal {\n" +
                "        System.out.println(\"Fly\");                      ||     void jump(){\n" +
                "    }                                                   ||         System.out.println(\"Jump\");\n" +
                "}                                                       ||     }\n" +
                "class Kangaroo extends Animal {                         || }\n" +
                "    @Override void move(){                              || public final class BadUseOfInstanceOf {\n" +
                "        System.out.println(\"Jump\");                     ||     public static void main(String[] args){\n" +
                "    }                                                   ||         makeItMove(new Fish());\n" +
                "}                                                       ||         makeItMove(new Bird());\n" +
                "public final class ProperUseOfInstanceOf {              ||         makeItMove(new Kangaroo());\n" +
                "    public static void main(String[] args){             ||     }\n" +
                "        makeItMove(new Fish());                         ||     public static void makeItMove(Animal animal){\n" +
                "        makeItMove(new Bird());                         ||         if (animal instanceof Fish){\n" +
                "        makeItMove(new Kangaroo());                     ||             Fish fish = (Fish)animal;\n" +
                "    }                                                   ||             fish.swim();\n" +
                "    public static void makeItMove(Animal animal){       ||         }\n" +
                "        animal.move();                                  ||         else if (animal instanceof Bird){\n" +
                "    }                                                   ||             Bird bird = (Bird)animal;\n" +
                "}                                                       ||             bird.fly();\n" +
                "                                                        ||         }\n" +
                "                                                        ||         else if (animal instanceof Kangaroo){\n" +
                "                                                        ||             Kangaroo kangaroo = (Kangaroo)animal;\n" +
                "                                                        ||             kangaroo.jump();\n" +
                "                                                        ||         }\n" +
                "                                                        ||     }\n" +
                "                                                        || }");
    }

    //CHAPTER 7
    private static void section7_1(){
        System.out.println("File Systems Overview");
        System.out.println("----------------------------------------");
        System.out.println("---------- Text File ----------");
        System.out.println();
        System.out.println("--- Write ---");
        System.out.println("PrintWriter outputFile = new PrintWriter(\"textFileName.txt\");");
        System.out.println();
        System.out.println("--- Read ---");
        System.out.println("Scanner inputFile = new Scanner(new File(\"textFileName.txt\"));");
        System.out.println();
        System.out.println("---------- Binary File ----------");
        System.out.println();
        System.out.println("--- Write ---");
        System.out.println("FileOutputStream outStream = new FileOutputStream(\"binaryFileName.dat\");");
        System.out.println("DataOutputStream outputFile = new DataOutputStream(outStream);");
        System.out.println();
        System.out.println("--- Read ---");
        System.out.println("FileInputStream inStream = new FileInputStream(\"binaryFileName.dat\");");
        System.out.println("DataInputStream inputFile = new DataInputStream(inStream);");
        System.out.println();
        System.out.println("---------- Random Access File ----------");
        System.out.println();
        System.out.println("--- Write ---");
        System.out.println("RandomAccessFile randomFile = new RandomAccessFile(\"randomAccessName.dat\", \"rw\");");
        System.out.println();
        System.out.println("--- Read ---");
        System.out.println("RandomAccessFile randomFile = new RandomAccessFile(\"randomAccessName.dat\", \"r\");");
        System.out.println();
        System.out.println("---------- Serializable File ----------");
        System.out.println();
        System.out.println("--- Write ---");
        System.out.println("FileOutputStream outStream = new FileOutputStream(\"serializedFileName.dat\");");
        System.out.println("ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);");
        System.out.println();
        System.out.println("--- Read ---");
        System.out.println("FileInputStream inStream = new FileInputStream(\"serializedFileName.dat\");");
        System.out.println("ObjectInputStream objectInputFile = new ObjectInputStream(inStream);");
        System.out.println();
    }
    private static void section7_2(){
        System.out.println("Text File Writing (.txt)");
        System.out.println("----------------------------------------");
        System.out.println("--- JDK 1.0 ---");
        System.out.println("*PrintStream is an object in the java.io package that lets you print output to a destination such as a file.*");
        System.out.println("Constructor = PrintStream(OutputStream out, boolean autoFlush, String charsetName)");
        System.out.println("    - Any methods you have used on System.out (such as print, println) will work on a PrintStream.");
        System.out.println("    - If the given file does not exist, it is created.");
        System.out.println("    - If the given file already exists, it is overwritten.");
        System.out.println("    - Do not open the same file for both reading (Scanner) and writing (PrintStream) at the same time.");
        System.out.println("        - You will overwrite your input file with an empty file (0 bytes).");
        System.out.println();
        System.out.println("    EX:");
        System.out.println("PrintStream output = new PrintStream(new File(\"out.txt\"));\n" +
                "output.println(\"Hello, file!\");\n" +
                "output.println(\"This is a second line of output.\");\n");
        System.out.println();
        System.out.println("//The console output object, System.out, is a PrintStream.");
        System.out.println("PrintStream out1 = System.out;\n" +
                "PrintStream out2 = new PrintStream(new File(\"data.txt\"));\n" +
                "out1.println(\"Hello, console!\");   // goes to console\n" +
                "out2.println(\"Hello, file!\");      // goes to file\n");
        System.out.println();
        System.out.println("--- JDK 1.1 ---");
        System.out.println("*PrintWriter is an object in the java.io package, similar to PrintStream.*");
        System.out.println("Constructor = PrintWriter(Writer wr) && PrintWriter(Writer wr, boolean autoFlush)");
        System.out.println("    - Any methods you have used on System.out (such as print, println) will work on a PrintWriter.");
        System.out.println("    - writer.printf() & writer.format() are the same thing");
        System.out.println("    - As a general rule:  ");
        System.out.println("        -If you're writing binary (or mixed) data use OutputStream instances.");
        System.out.println("        -If you're writing character data, use Writer instances.");
        System.out.println();
        System.out.println("    EX:");
        System.out.println("private static void WriteFile() throws FileNotFoundException {\n" +
                "    int[] numbers = { 10, 20, 30, 40, 50 };\n" +
                "\n" +
                "    // Open the file.\n" +
                "    PrintWriter outputFile = new PrintWriter(\"src/ArrayPractice/Values.txt\");\n" +
                "\n" +
                "    // Write the array elements to the file.\n" +
                "    for (int index = 0; index < numbers.length; index++){\n" +
                "        outputFile.println(numbers[index]);\n" +
                "    }\n" +
                "\n" +
                "    // Close the file.\n" +
                "    outputFile.close();\n" +
                "}");
    }
    private static void section7_3(){
        System.out.println("Text File Reading (.txt)");
        System.out.println("----------------------------------------");
        System.out.println("---Location---");
        System.out.println("Scanner input = new Scanner(new File(\"data/readme.txt\"));");
        System.out.println("//If our program is in A:/hw6");
        System.out.println("//Scanner will look for A:/hw6/data/readme.txt");
        System.out.println();
        System.out.println("Scanner input = new Scanner(new File(\"src/Kuroware/CS210/FileProcessingEx/Data/student.txt\"));");
        System.out.println("//If our program is in C:/intellijCodeRepository/KuroKage/src/Kuroware/CS210/FileProcessingEx");
        System.out.println("//Scanner will look for C:/intellijCodeRepository/KuroKage/src/Kuroware/CS210/FileProcessingEx/Data/student.txt");
        System.out.println();
        System.out.println("---Throws clause---");
        System.out.println("public static typename (params) throws type {");
        System.out.println("public static void main(String[] args) throws FileNotFoundException {");
        System.out.println("Similar to saying, \"I hereby announce that this method might throw an exception, and I accept the consequences if this happens.\"");
        System.out.println();
        System.out.println("---Input Tokens---");
        System.out.println("Token: A unit of user input, separated by whitespace. ");
        System.out.println("(A Scanner splits a file's contents into tokens.)");
        System.out.println("If an input file contains the following:    23   3.14  \"John Smith\"");
        System.out.println("Then the Scanner can interpret the tokens as the following types:");
        System.out.println("    Token    | Types");
        System.out.println("    --------------");
        System.out.println("    23       | int, double, String");
        System.out.println("    3.14     | double, String");
        System.out.println("    \"John    | String");
        System.out.println("    Smith\"   | String");
        System.out.println("Create Tokens with String Method:  public String[] split(String regex, int limit)");
        System.out.println("Ex: String[] tokens = str.split(\",\"); // \",\" = delimiter");
        System.out.println();
        System.out.println("         // Counts the words on each line of a file\n" +
                "        Scanner input = new Scanner(new File(\"input.txt\"));\n" +
                "        while (input.hasNextLine()) {\n" +
                "            String line = input.nextLine();\n" +
                "            Scanner lineScan = new Scanner(line);\n" +
                "\n" +
                "            // process the contents of this line\n" +
                "            int count = 0;\n" +
                "            while (lineScan.hasNext()) {\n" +
                "                String word = lineScan.next();\n" +
                "                count++;\n" +
                "            }\n" +
                "            System.out.println(\"Line has \" + count + \" words\");\n" +
                "        }");
        System.out.println("-----");
        System.out.println("Do not open the same file for both reading (Scanner) and writing (PrintStream) at the same time.\n" +
                "You will overwrite your input file with an empty file (0 bytes).\n");
    }
    private static void section7_4(){
        System.out.println("Binary File Writing (.dat)");
        System.out.println("----------------------------------------");
        System.out.println("public class WriteBinaryFile\n" +
                "{\n" +
                "    public static void main(String[] args) throws IOException {\n" +
                "        // An array to write to the file\n" +
                "        int[] numbers = {2, 4, 6, 8, 10, 12, 14};\n" +
                "\n" +
                "        // Create the binary output objects.\n" +
                "        FileOutputStream fstream = new FileOutputStream(\"src/BinaryFiles/Data/Numbers.dat\");\n" +
                "        DataOutputStream outputFile = new DataOutputStream(fstream);\n" +
                "        //To Append existing data use: FileOutputStream fstream = new FileOutputStream(\"src/BinaryFiles/Data/Numbers.dat\", true);\n" +
                "        //To overwrite existing data use single parameter constructor or use: FileOutputStream fstream = new FileOutputStream(\"src/BinaryFiles/Data/Numbers.dat\", false);\n" +
                "\n" +
                "        System.out.println(\"Writing the numbers to the file...\");\n" +
                "\n" +
                "        // Write the array elements to the file.\n" +
                "        for (int i = 0; i < numbers.length; i++) {\n" +
                "            outputFile.writeInt(numbers[i]);\n" +
                "        }\n" +
                "        System.out.println(\"Done.\");\n" +
                "\n" +
                "        // Close the file.\n" +
                "        outputFile.close();\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "// DataOutputStream Methods\n" +
                "/*\n" +
                "void close()                    Closes the file.\n" +
                "void writeBoolean(boolean b)    Writes the boolean value passed to b to the file.\n" +
                "void writeByte(byte b)          Writes the byte value passed to b to the file.\n" +
                "void writeChar(int c)           This method accepts an int, which is assumed to be a character code. The character it represents is written to the file as a two-byte Unicode character.\n" +
                "void writeDouble(double d)      Writes the double value passed to d to the file.\n" +
                "void writeFloat(float f)        Writes the float value passed to f to the file.\n" +
                "void writeInt(int i)            Writes the int value passed to i to the file.\n" +
                "void writeLong(long num)        Writes the long value passed to num to the file.\n" +
                "void writeShort(short s)        Writes the short value passed to s to the file.\n" +
                "void writeUTF(String str)       Writes the String object passed to str to the file using the Unicode Text Format.\n" +
                " */\n");
    }
    private static void section7_5(){
        System.out.println("Binary File Reading (.dat)");
        System.out.println("----------------------------------------");
        System.out.println("public class ReadBinaryFile {\n" +
                "    public static void main(String[] args) throws IOException {\n" +
                "        int number; // A number read from the file\n" +
                "        boolean endOfFile = false; // EOF flag\n" +
                "\n" +
                "        // Create the binary file input objects.\n" +
                "        FileInputStream fstream = new FileInputStream(\"src/BinaryFiles/Data/Numbers.dat\");\n" +
                "        DataInputStream inputFile = new DataInputStream(fstream);\n" +
                "\n" +
                "        System.out.println(\"Reading numbers from the file:\");\n" +
                "\n" +
                "        // Read the contents of the file.\n" +
                "        while (!endOfFile)\n" +
                "        {\n" +
                "            try\n" +
                "            {\n" +
                "                number = inputFile.readInt();\n" +
                "                System.out.print(number + \" \");\n" +
                "            }\n" +
                "            catch (EOFException e)\n" +
                "            {\n" +
                "                endOfFile = true;\n" +
                "            }\n" +
                "        }\n" +
                "        System.out.println(\"Done.\");\n" +
                "\n" +
                "        // Close the file.\n" +
                "        inputFile.close();\n" +
                "    }\n" +
                "}\n" +
                "// DataInputStream Methods\n" +
                "/*\n" +
                "void close()            Closes the file.\n" +
                "boolean readBoolean()   Reads a boolean value from the file and returns it.\n" +
                "byte readByte()         Reads a byte value from the file and returns it.\n" +
                "char readChar()         Reads a char value from the file and returns it. The character is expected to be stored as a two-byte Unicode character, as written by the DataOutputStream class's writeChar method.\n" +
                "double readDouble()     Reads a double value from the file and returns it.\n" +
                "float readFloat()       Reads a float value from the file and returns it.\n" +
                "int readInt()           Reads an int value from the file and returns it.\n" +
                "long readLong()         Reads a long value from the file and returns it.\n" +
                "short readShort()       Reads a short value from the file and returns it.\n" +
                "String readUTF()        Reads a string from the file and returns it as a String object. The string must have been written with the DataOutputStream class's writeUTF method.\n" +
                " */");
    }
    private static void section7_6(){
        System.out.println("Random Access File Writing (.dat)");
        System.out.println("----------------------------------------");
        System.out.println("----- CODE: -----");
        System.out.println("public class WriteRandomLetters\n" +
                "{\n" +
                "    public static void main(String[] args) throws IOException\n" +
                "    {\n" +
                "        // The letters array has all 26 letters.\n" +
                "        char[] letters = {\n" +
                "                'a', 'b', 'c', 'd', 'e', 'f', 'g',\n" +
                "                'h', 'i', 'j', 'k', 'l', 'm', 'n',\n" +
                "                'o', 'p', 'q', 'r', 's', 't', 'u',\n" +
                "                'v', 'w', 'x', 'y', 'z' };\n" +
                "\n" +
                "        System.out.println(\"Opening the file.\");\n" +
                "\n" +
                "        // Open a file for reading and writing.\n" +
                "        RandomAccessFile randomFile = new RandomAccessFile(\"src/RandomAccessFiles/Data/Letters.dat\", \"rw\");\n" +
                "\n" +
                "        System.out.println(\"Writing data to the file...\");\n" +
                "\n" +
                "        // Sequentially write the letters array to the file.\n" +
                "        for (int i = 0; i < letters.length; i++) {\n" +
                "            randomFile.writeChar(letters[i]);\n" +
                "        }\n" +
                "\n" +
                "        // Close the file.\n" +
                "        randomFile.close();\n" +
                "\n" +
                "        System.out.println(\"Done.\");\n" +
                "    }\n" +
                "}");
        System.out.println("----- CONSOLE: -----");
        System.out.println("Opening the file.\n" +
                "Writing data to the file...\n" +
                "Done.");
    }
    private static void section7_7(){
        System.out.println("Random Access File Reading (.dat)");
        System.out.println("----------------------------------------");
        System.out.println("----- CODE: -----");
        System.out.println("public class ReadRandomLetters\n" +
                "{\n" +
                "    public static void main(String[] args) throws IOException\n" +
                "    {\n" +
                "        final int CHAR_SIZE = 2; // 2 byte characters\n" +
                "        long byteNum; // The byte number\n" +
                "        char ch; // A character from the file\n" +
                "\n" +
                "        // Open the file for reading.\n" +
                "        RandomAccessFile randomFile = new RandomAccessFile(\"src/RandomAccessFiles/Data/Letters.dat\", \"r\");\n" +
                "\n" +
                "        // Move to the character 5. This is the 6th character from the beginning of the file.\n" +
                "        byteNum = CHAR_SIZE * 5;\n" +
                "        randomFile.seek(byteNum); // a b c d e| f g h i j k l m n o p q r s t u v w x y z\n" +
                "\n" +
                "        // Read the character stored at this location and display it. Should be the letter f.\n" +
                "        ch = randomFile.readChar(); // a b c d e f| g h i j k l m n o p q r s t u v w x y z\n" +
                "        System.out.println(ch);\n" +
                "\n" +
                "\n" +
                "        // Move to character 10 (the 11th character), read the character, and display it. Should be the letter k.\n" +
                "        byteNum = CHAR_SIZE * 10;\n" +
                "        randomFile.seek(byteNum); // a b c d e f g h i j| k l m n o p q r s t u v w x y z\n" +
                "        ch = randomFile.readChar(); // a b c d e f g h i j k| l m n o p q r s t u v w x y z\n" +
                "        System.out.println(ch);\n" +
                "\n" +
                "        // Move to character 3 (the 4th character), read the character, and display it. Should be the letter d.\n" +
                "        byteNum = CHAR_SIZE * 3;\n" +
                "        randomFile.seek(byteNum); // a b c| d e f g h i j k l m n o p q r s t u v w x y z\n" +
                "        ch = randomFile.readChar(); // a b c d| e f g h i j k l m n o p q r s t u v w x y z\n" +
                "        System.out.println(ch);\n" +
                "\n" +
                "        // Close the file.\n" +
                "        randomFile.close();\n" +
                "    }\n" +
                "}");
        System.out.println("----- CONSOLE: -----");
        System.out.println("f\n" +
                "k\n" +
                "d");
    }
    private static void section7_8(){
        System.out.println("Object File Writing (Serialization)(.dat)");
        System.out.println("----------------------------------------");
        System.out.println("----- CODE: -----");
        System.out.println("public class SerializeObjects\n" +
                "{\n" +
                "    public static void main(String[] args) throws IOException\n" +
                "    {\n" +
                "        final int NUM_ITEMS = 3; // Number of Ninja's\n" +
                "\n" +
                "        // Create a Scanner object for keyboard input.\n" +
                "        Scanner keyboard = new Scanner(System.in);\n" +
                "\n" +
                "        // Create a Ninja's array\n" +
                "        Ninja[] ninjas = new Ninja[NUM_ITEMS];\n" +
                "\n" +
                "        // Populate the array.\n" +
                "        for (int i = 0; i < ninjas.length; i++)\n" +
                "        {\n" +
                "            // Get an ninja name.\n" +
                "            System.out.println(\"Enter the name for Ninja \" + (i + 1) + \": \");\n" +
                "            String name = keyboard.nextLine();\n" +
                "\n" +
                "            // Create an object in the array.\n" +
                "            ninjas[i] = new Ninja(name);\n" +
                "        }\n" +
                "\n" +
                "        // Create the stream objects.\n" +
                "        FileOutputStream outStream = new FileOutputStream(\"src/RandomAccessFilez/Data/NinjaObjects.dat\");\n" +
                "        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);\n" +
                "\n" +
                "        // Write the serialized objects to the file.\n" +
                "        for (int i = 0; i < ninjas.length; i++)\n" +
                "        {\n" +
                "            objectOutputFile.writeObject(ninjas[i]);\n" +
                "        }\n" +
                "\n" +
                "        // Close the file.\n" +
                "        objectOutputFile.close();\n" +
                "\n" +
                "        System.out.println(\"The serialized objects were written to the NinjaObjects.dat file.\");\n" +
                "    }\n" +
                "}");
        System.out.println("----- CONSOLE: -----");
        System.out.println("Enter the name for Ninja 1: Naruto\n" +
                "Enter the name for Ninja 2: Sasuke\n" +
                "Enter the name for Ninja 3: Sakura\n" +
                "The serialized objects were written to the NinjaObjects.dat file.");
    }
    private static void section7_9(){
        System.out.println("Object File Reading (Deserialization)(.dat)");
        System.out.println("----------------------------------------");
        System.out.println("----- CODE: -----");
        System.out.println("public class DeserializeObjects\n" +
                "{\n" +
                "    public static void main(String[] args) throws Exception\n" +
                "    {\n" +
                "        final int NUM_ITEMS = 3; // Number of Ninja's\n" +
                "\n" +
                "        // Create the stream objects.\n" +
                "        FileInputStream inStream = new FileInputStream(\"src/RandomAccessFilez/Data/NinjaObjects.dat\");\n" +
                "        ObjectInputStream objectInputFile = new ObjectInputStream(inStream);\n" +
                "\n" +
                "        // Create a Ninja[] array\n" +
                "        Ninja[] ninjas = new Ninja[NUM_ITEMS];\n" +
                "\n" +
                "        // Read the serialized objects from the file.\n" +
                "        for (int i = 0; i < ninjas.length; i++)\n" +
                "        {\n" +
                "            ninjas[i] = (Ninja) objectInputFile.readObject();\n" +
                "        }\n" +
                "\n" +
                "        // Close the file.\n" +
                "        objectInputFile.close();\n" +
                "\n" +
                "        // Display the objects.\n" +
                "        for (int i = 0; i < ninjas.length; i++)\n" +
                "        {\n" +
                "            System.out.print(\"Ninja \" + (i + 1) + \": \");\n" +
                "            ninjas[i].greeting();\n" +
                "        }\n" +
                "    }\n" +
                "}");
        System.out.println("----- CONSOLE: ------");
        System.out.println("Ninja 1: Hello, my name is Naruto\n" +
                "Ninja 2: Hello, my name is Sasuke\n" +
                "Ninja 3: Hello, my name is Sakura");
    }

    //CHAPTER 8
    private static void section8_1(){
        System.out.println("Try & Catch Statements");
        System.out.println("----------------------------------------");
        System.out.println("--- CODE: ---");
        System.out.println("public class Testing {\n" +
                "    public static void main(String[] args)\n" +
                "    {\n" +
                "        //Variables\n" +
                "        int num;\n" +
                "        String str;\n" +
                "\n" +
                "        //Try, Catch then Finally\n" +
                "        try {\n" +
                "            str = \"xyz\";\n" +
                "            num = Integer.parseInt(str);\n" +
                "            System.out.println(\"A\");\n" +
                "        }\n" +
                "        catch(NumberFormatException e) {\n" +
                "            System.out.println(\"B\");\n" +
                "        }\n" +
                "        catch(IllegalArgumentException e) {\n" +
                "            System.out.println(\"C\");\n" +
                "        }\n" +
                "        finally {\n" +
                "            System.out.println(\"D\");\n" +
                "        }\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE: ---");
        System.out.println("B\n" +
                "D");
    }
    private static void section8_2(){
        System.out.println("Exception Hierarchy");
        System.out.println("----------------------------------------");
        System.out.println("Exception Types:");
        System.out.println("Unchecked* = inherit from the \"Error\" || \"RuntimeException\" classes. (unchecked exceptions are also known as runtime exceptions.)");
        System.out.println("Checked =  all remaining throwable objects. (checked exceptions are also known as compileTime exceptions.)");
        System.out.println();
        System.out.println("Goal:");
        System.out.println("All checked exceptions should be handled by the developer within their program");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Hierarchy:");
        System.out.println();
        System.out.println("Object");
        System.out.println("│  ");
        System.out.println("Throwable");
        System.out.println("├── Error*");
        System.out.println("│     ├── VirtualMachineError*");
        System.out.println("│     │     ├── StackOverflowError");
        System.out.println("│     │     ├── OutOfMemory");
        System.out.println("│     │     └── . . .");
        System.out.println("│     │   ");
        System.out.println("│     ├── AssertionError");
        System.out.println("│     ├── IOError");
        System.out.println("│     └── . . .");
        System.out.println("│  ");
        System.out.println("└── Exception");
        System.out.println("      ├── RuntimeException*");
        System.out.println("      │     ├── IndexOutOfBoundsException");
        System.out.println("      │     │     └── ArrayIndexOutOfBoundsException");
        System.out.println("      │     ├── IllegalArgumentException");
        System.out.println("      │     │     └── NumberFormatException");
        System.out.println("      │     ├── NullPointerException");
        System.out.println("      │     ├── IllegalStateException");
        System.out.println("      │     ├── ClassCastException");
        System.out.println("      │     ├── ArithmeticException");
        System.out.println("      │     └── . . .");
        System.out.println("      │   ");
        System.out.println("      ├── IOException");
        System.out.println("      │     ├── EOFException");
        System.out.println("      │     ├── FileNotFoundException");
        System.out.println("      │     ├── MalformedURLException");
        System.out.println("      │     ├── UnknownHostException");
        System.out.println("      │     └── . . .");
        System.out.println("      │   ");
        System.out.println("      ├── SQLException");
        System.out.println("      ├── ClassNotFoundException");
        System.out.println("      ├── InterruptedException");
        System.out.println("      └── . . .");

                //Tree Building
        //        System.out.println("└──");
        //        System.out.println("├──");
        //        System.out.println("│  ");
        //        System.out.println("   ");
    }
    private static void section8_3(){
        System.out.println("Crafting Exceptions");
        System.out.println("----------------------------------------");
        System.out.println("--- CODE: ---");
        //Custom Exception
        System.out.println("public class DieException extends IllegalArgumentException {\n" +
                "    public DieException() { //Default Constructor\n" +
                "        super(\"The die must have at least 4 sides.\");\n" +
                "    }\n" +
                "    \n" +
                "    public DieException(int sides) { //x1 int Parameter Constructor\n" +
                "        super(\"The die must have at least 4 sides. This die has \"+sides+\".\");\n" +
                "    }\n" +
                "}\n");
        //Die Class
        System.out.println("public class Die {\n" +
                "    private int sides;\n" +
                "    private Random rand = new Random();\n" +
                "\n" +
                "    public Die(int numSides) {\n" +
                "        // Validate the number of sides.\n" +
                "        if (numSides < 4) {\n" +
                "            throw new DieException(numSides);\n" +
                "        }\n" +
                "\n" +
                "        this.sides = numSides;\n" +
                "        roll();\n" +
                "    }\n" +
                "    public int roll() {\n" +
                "        int value = rand.nextInt(sides) + 1;\n" +
                "        return value;\n" +
                "    }\n" +
                "}");
        //Main Class
        System.out.println("public class DiceExceptionExample {\n" +
                "    public static void main(String[] args) {\n" +
                "        Die die = new Die(2); //Create new Die Object && *Trigger Exception*\n" +
                "\n" +
                "        System.out.println(\"First Roll = \"+die.roll()); //Print to console if valid\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE: ---");
        System.out.println("Exception in thread \"main\" HandlingExceptions.Manual.DieException: The die must have at least 4 sides. This die has 2.\n" +
                "\tat HandlingExceptions.Manual.Die.<init>(Die.java:13)\n" +
                "\tat HandlingExceptions.Manual.DiceExceptionExample.main(DiceExceptionExample.java:7)");
    }

    //CHAPTER 9
    private static void section9_1(){
        System.out.println("Setup Apache Derby");
        System.out.println("----------------------------------------");
        System.out.println("1. Download & Install Apache Derby (bin.zip) @link: https://db.apache.org/derby/derby_downloads.html");
        System.out.println("2. CMD -> Enter the next 5 commands");
        System.out.println("    ------------------------------------");
        System.out.println("set DERBY_INSTALL=C:\\Apache\\db-derby-10.15.2.0-bin");
        System.out.println("set CLASSPATH=%DERBY_INSTALL%\\lib\\derby.jar;%DERBY_INSTALL%\\lib\\derbytools.jar;.");
        System.out.println("cd %DERBY_INSTALL%\\bin");
        System.out.println("setEmbeddedCP.bat");
        System.out.println("java org.apache.derby.tools.sysinfo");
        System.out.println("    ------------------------------------");
        System.out.println("3. CMD -> cd C:\\Apache\\DerbyData -> ij -> connect 'jdbc:derby:CoffeeDB; create=true';");
        System.out.println("        This should have created a database at the location C:\\Apache\\DerbyData");
        System.out.println("4. Program Structure -> Modules -> Dependencies -> On the far right, click on the \"+\" sign and then select \"Jars or Directories\"");
        System.out.println("        Add: C:\\Apache\\db-derby-10.15.2.0-bin\\lib\\derby.jar");
        System.out.println("        Add: C:\\Apache\\db-derby-10.15.2.0-bin\\lib\\derbynet.jar");
        System.out.println("        Add: C:\\Apache\\db-derby-10.15.2.0-bin\\lib\\derbyclient.jar");
        System.out.println("        Add: C:\\Apache\\db-derby-10.15.2.0-bin\\lib\\derbytools.jar");
        System.out.println("5. Test & Enjoy DataBase");
        System.out.println();
        System.out.println("--- CODE: ---");
        System.out.println("public class TestConnection {\n" +
                "    public static void main(String[] args) {\n" +
                "        // Create a named constant for the URL (NOTE: This value is specific for either Java DB or Apache Derby)\n" +
                "        final String DB_URL = \"jdbc:derby:C:\\\\Apache\\\\DerbyData\\\\CoffeeDB\";\n" +
                "\n" +
                "        try {\n" +
                "            // Create a connection to the database.\n" +
                "            Connection conn = DriverManager.getConnection(DB_URL);\n" +
                "            System.out.println(\"Connection created to CoffeeDB.\");\n" +
                "\n" +
                "            // Close the connection.\n" +
                "            conn.close();\n" +
                "            System.out.println(\"Connection closed.\");\n" +
                "        }\n" +
                "        catch (Exception ex) {\n" +
                "            System.out.println(\"ERROR: \" + ex.getMessage());\n" +
                "        }\n" +
                "    }\n" +
                "}");
        System.out.println("--- CONSOLE: ---");
        System.out.println("Connection created to CoffeeDB.\n" +
                "Connection closed.");
        System.out.println("--- Using \"ij\" in CMD ---");
        System.out.println("1. Windows Key -> Type \"Edit the System Environment Variables\" -> Environment Variables -> Path -> Edit");
        System.out.println("2. Add Derby bin directory, Ex: \"C:\\Apache\\db-derby-10.15.2.0-bin\\bin\"");
        System.out.println("3. Okay -> Okay -> Okay");
        System.out.println("4. CMD -> ij -> ij Commands");
        System.out.println("--- CMD ---");
        System.out.println("ij");
        System.out.println("connect 'jdbc:derby:C:\\Apache\\DerbyData\\CoffeeDB';");
        System.out.println();
        //https://www.youtube.com/watch?v=UuXkwgPkVPg //Part 1 Install
        //https://www.youtube.com/watch?v=psCbBkD1d9g //Part 2 Work with in CMD
        //https://www.youtube.com/watch?v=M-BbeTigZAU //Part 3 Intellij
    }

    //Example Methods
    private static int sumDigits (int number){
        //125 < 10 = false
        if (number < 10) {
            return -1;
        }
        int sum = 0;
        // 125 > 0 = true
        // 12 > 0 = true
        // 1 > 0 = true
        // 0 > 0 = false
        while (number > 0) {
            //extract least significant digit
            // 125%10 = 5
            // 12%10 = 2
            // 1%10 = 1
            int digit = number % 10;
            sum += digit;
            // sum+5 = 5
            // sum+2 = 7
            // sum+1 = 8
            //drop least significant digit
            //125 / 10 = 12
            //12 / 10 = 1
            //1 / 10 = 0
            number /= 10; //same as... number = number/10;
        }
        return sum;
    }
}
