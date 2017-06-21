package com.raghu.template;

import com.intellij.ide.IdeBundle;
import com.intellij.ide.IdeView;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.JavaTemplateUtil;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.WriteActionAware;
import com.intellij.openapi.project.Project;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.PlatformIcons;

/**
 * Created by raghavendraguru on 6/20/17.
 */
public class TemplateClassAction extends AnAction implements WriteActionAware {

    private Project project;
    private TemplateDialog dialog;
    private PsiDirectory psiDirectory;
    private PsiFile psiFile;

    @Override
    public void actionPerformed(AnActionEvent e) {

//        psiFile = e.getData(LangDataKeys.PSI_FILE);
//        psiDirectory =  psiFile.getContainingDirectory();
//        project = e.getRequiredData(CommonDataKeys.PROJECT);
//        dialog = new TemplateDialog(project, false);
//        dialog.show();
//
//        if(dialog.isOK()){
//            performOkAction();
//        }


        final DataContext dataContext = e.getDataContext();

        final IdeView view = LangDataKeys.IDE_VIEW.getData(dataContext);
        if (view == null) {
            return;
        }

        final Project project = CommonDataKeys.PROJECT.getData(dataContext);

        final PsiDirectory dir = view.getOrChooseDirectory();
        if (dir == null || project == null) return;

        final CreateFileFromTemplateDialog.Builder builder = CreateFileFromTemplateDialog.createDialog(project);
        buildDialog(project, dir, builder);


    }

    private void buildDialog(Project project, PsiDirectory dir, CreateFileFromTemplateDialog.Builder builder) {

        builder
                .setTitle(IdeBundle.message("action.create.new.class"))
                .addKind("Class", PlatformIcons.CLASS_ICON, JavaTemplateUtil.INTERNAL_CLASS_TEMPLATE_NAME)
                .addKind("Interface", PlatformIcons.INTERFACE_ICON, JavaTemplateUtil.INTERNAL_INTERFACE_TEMPLATE_NAME);

    }

    private void performOkAction() {


    }

    @Override
    public void update(AnActionEvent e) {
        Project project = e.getProject();
        e.getPresentation().setVisible(project != null);
    }

}
