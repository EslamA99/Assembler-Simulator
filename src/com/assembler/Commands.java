package com.assembler;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Map;

public class Commands {

    public static void add(Map<String, Integer> mipsRegisters, ArrayList<String> args) {//add s0,s1,s2
        int temp = mipsRegisters.get(args.get(1)) + mipsRegisters.get(args.get(2));
        mipsRegisters.replace(args.get(0), temp);

    }

    public static void sub(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        int temp = mipsRegisters.get(args.get(1)) - mipsRegisters.get(args.get(2));
        mipsRegisters.replace(args.get(0), temp);

    }

    public static void and(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        mipsRegisters.replace(args.get(0), mipsRegisters.get(args.get(1)) & mipsRegisters.get(args.get(2)));
    }

    public static void or(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        mipsRegisters.replace(args.get(0), mipsRegisters.get(args.get(1)) | mipsRegisters.get(args.get(2)));

    }

    public static void sll(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        int temp = Integer.parseInt(args.get(2));
        mipsRegisters.replace(args.get(0), mipsRegisters.get(args.get(1)) << temp);

    }

    public static void slt(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        if (mipsRegisters.get(args.get(1)) < mipsRegisters.get(args.get(2)))
            mipsRegisters.replace(args.get(0), 1);
        else
            mipsRegisters.replace(args.get(0), 0);

    }

    public static String lw(int[] memory, Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        int num = Integer.parseInt(args.get(2));
        int numOfRegister = mipsRegisters.get(args.get(1)) + 1000;
        if (num + numOfRegister <= 2000 && num + numOfRegister >= 1000) {
            mipsRegisters.replace(args.get(0), memory[num + numOfRegister]);

            return "";
        } else return "Unvalid memory";
    }

    public static String sw(int[] memory, Map<String, Integer> mipsRegisters, ArrayList<String> args, DefaultTableModel tableModelMemory) {
        int num = Integer.parseInt(args.get(2));
        int numOfRegister = mipsRegisters.get(args.get(1)) + 1000;
        if (num + numOfRegister <= 2000 && num + numOfRegister >= 1000) {
            memory[num + numOfRegister] = mipsRegisters.get(args.get(0));
            String tmp = Integer.toString(memory[num + numOfRegister], 2);
            while (tmp.length() < 32)
                tmp = "0" + tmp;
            tableModelMemory.setValueAt(tmp, num + numOfRegister, 2);
            return "";
        } else return "Unvalid memory";
    }

    public static void addi(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        int temp = Integer.parseInt(args.get(2));
        mipsRegisters.replace(args.get(0), mipsRegisters.get(args.get(1)) + temp);

    }

    public static void andi(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        int temp = Integer.parseInt(args.get(2));
        mipsRegisters.replace(args.get(0), mipsRegisters.get(args.get(1)) & temp);

    }

    public static void ori(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        int temp = Integer.parseInt(args.get(2));
        mipsRegisters.replace(args.get(0), mipsRegisters.get(args.get(1)) | temp);

    }

    public static void slti(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        int temp = Integer.parseInt(args.get(2));
        if (mipsRegisters.get(args.get(1)) < temp)
            mipsRegisters.replace(args.get(0), 1);
        else
            mipsRegisters.replace(args.get(0), 0);
    }

    public static void lui(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        int temp = Integer.parseInt(args.get(1));
        mipsRegisters.replace(args.get(0), temp << 16);
    }

    public static int jr(Map<String, Integer> mipsRegisters, ArrayList<String> args) {
        return mipsRegisters.get(args.get(0));
    }

    public static int j(ArrayList<Instruction> codeLines, ArrayList<String> args) {
        for (int i = 0; i < codeLines.size(); i++) {
            if (codeLines.get(i).isLabel && codeLines.get(i).labelName.equals(args.get(0))) {

                return i;
            }
        }
        return -1;
    }

    public static int beq(Map<String, Integer> mipsRegisters, ArrayList<Instruction> codeLines, ArrayList<String> args, int currentLine) {

        int temp = mipsRegisters.get(args.get(1));// beq s0,s1,asdad
        if (mipsRegisters.get(args.get(0)) == temp) {
            for (int i = 0; i < codeLines.size(); i++) {
                if (codeLines.get(i).isLabel && codeLines.get(i).labelName.equals(args.get(2))) {
                    return i;
                }
            }
        } else
            return currentLine;
        return -1;
    }

    public static int bne(Map<String, Integer> mipsRegisters, ArrayList<Instruction> codeLines, ArrayList<String> args, int currentLine) {
        int temp = mipsRegisters.get(args.get(1));
        if (mipsRegisters.get(args.get(0)) != temp) {
            for (int i = 0; i < codeLines.size(); i++) {
                if (codeLines.get(i).isLabel && codeLines.get(i).labelName.equals(args.get(2))) {
                    return i;
                }
            }
        } else
            return currentLine;
        return -1;
    }

}
