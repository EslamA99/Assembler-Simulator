package com.assembler;

import java.util.ArrayList;
import java.util.function.BinaryOperator;

public class Instruction {
    String instruct;//
    String labelName;
    ArrayList<String> args = new ArrayList<>();
    Boolean isLabel = false;
    Boolean rType = false;
    Boolean iType = false;
    Boolean jType = false;


    public Instruction() {
    }

    public Instruction(String instruct, ArrayList<String> args, Boolean isLabel, Boolean rType, Boolean iType, Boolean jType) {
        this.instruct = instruct;
        this.args = args;
        this.isLabel = isLabel;
        this.rType = rType;
        this.iType = iType;
        this.jType = jType;
    }

    int setInstructionCode() {
        String instructionCode = "";
        if (this.rType) {
            if (!this.instruct.equals("sll") && !this.instruct.equals("jr")) {
                instructionCode += Parser.opcode.get(this.instruct);
                String temp = "";
                int indx = -1;
                indx = Parser.registerIndexes.indexOf(this.args.get(1));
                temp = Integer.toBinaryString(indx);
                while (temp.length() < 5) {
                    temp = "0" + temp;
                }
                instructionCode += temp;

                indx = Parser.registerIndexes.indexOf(this.args.get(2));
                temp = Integer.toBinaryString(indx);
                while (temp.length() < 5) {
                    temp = "0" + temp;
                }
                instructionCode += temp;
                indx = Parser.registerIndexes.indexOf(this.args.get(0));
                temp = Integer.toBinaryString(indx);
                while (temp.length() < 5) {
                    temp = "0" + temp;
                }
                instructionCode += temp;
                instructionCode += "00000";
                instructionCode += Parser.functcode.get(this.instruct);

            } else if (this.instruct.equals("jr")) {
                instructionCode += Parser.opcode.get(this.instruct);
                String temp = "";
                int indx = -1;
                instructionCode += "00000" + "00000";
                indx = Parser.registerIndexes.indexOf(this.args.get(0));
                temp = Integer.toBinaryString(indx);
                while (temp.length() < 5) {
                    temp = "0" + temp;
                }
                instructionCode += temp;
                instructionCode += "00000";
                instructionCode += Parser.functcode.get(this.instruct);
            } else {
                instructionCode += Parser.opcode.get(this.instruct);
                String temp = "";
                int indx = -1;
                instructionCode += "00000";

                indx = Parser.registerIndexes.indexOf(this.args.get(1));
                temp = Integer.toBinaryString(indx);
                while (temp.length() < 5) {
                    temp = "0" + temp;
                }
                instructionCode += temp;
                indx = Parser.registerIndexes.indexOf(this.args.get(0));
                temp = Integer.toBinaryString(indx);
                while (temp.length() < 5) {
                    temp = "0" + temp;
                }
                instructionCode += temp;
                temp = Integer.toBinaryString(Integer.valueOf(this.args.get(2)));
                while (temp.length() < 5) {
                    temp = "0" + temp;
                }
                instructionCode += Parser.functcode.get(this.instruct);
            }

        } else if (this.iType) {
            instructionCode = Parser.opcode.get(this.instruct);
            String temp = "";
            int indx = Parser.registerIndexes.indexOf(this.args.get(0));
            temp = Integer.toBinaryString(indx);
            while (temp.length() < 5) {
                temp = "0" + temp;
            }
            instructionCode += temp;
            indx = Parser.registerIndexes.indexOf(this.args.get(1));
            temp = Integer.toBinaryString(indx);
            while (temp.length() < 5) {
                temp = "0" + temp;
            }
            instructionCode += temp;
            temp = Integer.toBinaryString(Integer.valueOf(this.args.get(2)));
            while (temp.length() < 16) {
                temp = "0" + temp;
            }
            instructionCode += temp;


        } else {
            instructionCode += Parser.opcode.get(this.instruct);
        }
        if(instructionCode.charAt(0)=='1')
            return -1*Integer.parseInt(instructionCode.substring(1),2);
        return Integer.parseInt(instructionCode,2);
    }
    int specialCaseCode(int labelPos){
        String instructionCode="";
        if(instruct.equals("beq")||instruct.equals("bne")){
            instructionCode = Parser.opcode.get(this.instruct);
            String temp = "";
            int indx = Parser.registerIndexes.indexOf(this.args.get(0));
            temp = Integer.toBinaryString(indx);
            while (temp.length() < 5) {
                temp = "0" + temp;
            }
            instructionCode += temp;
            indx = Parser.registerIndexes.indexOf(this.args.get(1));
            temp = Integer.toBinaryString(indx);
            while (temp.length() < 5) {
                temp = "0" + temp;
            }
            instructionCode += temp;
            temp=Integer.toBinaryString(labelPos);
            while(temp.length()<16)
                temp="0"+temp;
            instructionCode+=temp;
        }
        else if(instruct.equals("j")){
            instructionCode = Parser.opcode.get(this.instruct);
            String temp=Integer.toBinaryString(labelPos);
            while(temp.length()<26)
                temp="0"+temp;
            instructionCode+=temp;
        }
        if(instructionCode.charAt(0)=='1')
            return -1*Integer.parseInt(instructionCode.substring(1),2);
        return Integer.parseInt(instructionCode,2);
    }
}
