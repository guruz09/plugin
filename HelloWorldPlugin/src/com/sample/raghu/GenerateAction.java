package com.sample.raghu;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.util.PsiTreeUtil;

import java.awt.event.ActionEvent;
import java.util.List;

import static com.intellij.testFramework.LightPlatformTestCase.getProject;

/**
 * Created by raghavendraguru on 6/19/17.
 */
public class GenerateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiClass psiClass = getPsiClass(e);
        ShowDialog dialog = new ShowDialog(psiClass);
        dialog.show();

        if(dialog.isOK()){
            generateHelloFunc(psiClass, dialog.getFileds());
        }
    }

    private void generateHelloFunc(PsiClass psiClass, List<PsiField> fields) {
        new WriteCommandAction.Simple(psiClass.getProject(), psiClass.getContainingFile()){

            @Override
            protected void run() throws Throwable {
                generateMethod(psiClass, fields);
            }
        }.execute();
    }

    private void generateMethod(PsiClass psiClass, List<PsiField> fields){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("public void sayHello(){"+'\n');
        for (PsiField field :  fields) {
            stringBuilder.append('\t'+"System.out.println("+'"'+ "Hello from    " +'"'+" "+ "+" + " " + field.getName()  + ");\n");
        }
        stringBuilder.append("}");

        PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(psiClass.getProject());
        PsiMethod sayHello = elementFactory.createMethodFromText(stringBuilder.toString(), psiClass);
        PsiElement method =  psiClass.add(sayHello);
        JavaCodeStyleManager.getInstance(psiClass.getProject()).shortenClassReferences(method);
    }

    @Override
    public void update(AnActionEvent e) {

        PsiClass psiClass = getPsiClass(e);
        if (psiClass == null) {
            e.getPresentation().setEnabled(false);
        }

    }

    public PsiClass getPsiClass(AnActionEvent e){
        PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
        Editor  editor = e.getData(PlatformDataKeys.EDITOR);

        if (editor == null || psiFile == null) {
            return null;
        }

        int offset = editor.getCaretModel().getOffset();
        PsiElement psiElement = psiFile.findElementAt(offset);

        PsiClass psiClass = PsiTreeUtil.getParentOfType(psiElement, PsiClass.class);

        return psiClass;
    }


}
