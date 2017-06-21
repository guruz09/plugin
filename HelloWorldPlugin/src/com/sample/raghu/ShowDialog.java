package com.sample.raghu;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

/**
 * Created by raghavendraguru on 6/19/17.
 */
public class ShowDialog extends DialogWrapper{

    private CollectionListModel<PsiField> fieldCollectionListModel;
    private LabeledComponent<JPanel> labeledComponent;

    public ShowDialog(PsiClass psiClass) {

        super(psiClass.getProject());
        setTitle("Select the fields");

        fieldCollectionListModel = new CollectionListModel<>(psiClass.getAllFields());
        JBList fieldsSet = new JBList(fieldCollectionListModel);
        fieldsSet.setCellRenderer(new DefaultListCellRenderer());
        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(fieldsSet);
        JPanel panel = decorator.createPanel();

        labeledComponent = LabeledComponent.create(panel, "Select the fields");

        init();

    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return labeledComponent;
    }

    public List<PsiField> getFileds(){
        return fieldCollectionListModel.getItems();
    }
}
