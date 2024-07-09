package com.example.application.views.categoriatarefa;

import java.util.Collections;
import java.util.List;

import com.example.application.controller.CategoriaTarefaController;
import com.example.application.models.CategoriaTarefa;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Categoria Tarefa")
@Route(value = "Categoria-Tarefa", layout = MainLayout.class)
@RouteAlias(value = "Categoria-Tarefa", layout = MainLayout.class)
public class CategoriaTarefaView extends Composite<VerticalLayout> {

    private CategoriaTarefaController categoriaTarefaController;
    private Grid<CategoriaTarefa> gridConsulta;

    public CategoriaTarefaView() {

        categoriaTarefaController = new CategoriaTarefaController();

        Tab tabGerenciar = new Tab("Gerenciar Categoria Tarefa");
        Tab tabConsultar = new Tab("Consultar Categoria Tarefa");
        Tabs tabs = new Tabs(tabGerenciar, tabConsultar);

        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textFieldID = new TextField();
        TextField textFieldDescricao = new TextField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonSalvar = new Button();
        Button buttonAlterar = new Button();
        Button buttonExcluir = new Button();
        Button buttonPesquisar = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Gerenciar Categoria");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textFieldID.setLabel("ID");
        textFieldDescricao.setLabel("Descrição");
        textFieldDescricao.setWidth("56%");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonSalvar.setText("Salvar");
        buttonSalvar.setWidth("min-content");
        buttonSalvar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonAlterar.setText("Alterar");
        buttonAlterar.setWidth("min-content");
        buttonAlterar.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        buttonExcluir.setText("Excluir");
        buttonExcluir.setWidth("min-content");
        buttonExcluir.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonPesquisar.setText("Pesquisar");
        buttonPesquisar.setWidth("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textFieldID);
        formLayout2Col.add(textFieldDescricao);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonSalvar);
        layoutRow.add(buttonAlterar);
        layoutRow.add(buttonExcluir);
        layoutRow.add(buttonPesquisar);

        VerticalLayout layoutConsulta = new VerticalLayout();
        H3 h3Consulta = new H3("Consultar Categoria Tarefa");
        gridConsulta = new Grid<>(CategoriaTarefa.class);
        layoutConsulta.add(h3Consulta, gridConsulta);
        layoutConsulta.setVisible(false);

        getContent().add(tabs, layoutColumn2, layoutConsulta);
        layoutColumn2.add(h3, textFieldID, textFieldDescricao, layoutRow);
        layoutRow.add(buttonSalvar, buttonAlterar, buttonExcluir, buttonPesquisar);

        tabs.addSelectedChangeListener(event -> {
            boolean isGerenciarTabSelected = tabs.getSelectedTab() == tabGerenciar;
            layoutColumn2.setVisible(isGerenciarTabSelected);
            layoutConsulta.setVisible(!isGerenciarTabSelected);
            if (!isGerenciarTabSelected) {
                atualizarGridConsulta();
            }
        });

        gridConsulta.addItemDoubleClickListener(event -> {
            CategoriaTarefa categoriaTarefa = event.getItem();
            if (categoriaTarefa != null) {
                textFieldID.setValue(String.valueOf(categoriaTarefa.getId()));
                textFieldDescricao.setValue(categoriaTarefa.getDescricao());
                tabs.setSelectedTab(tabGerenciar); // Alterna para a aba "Gerenciar Prioridade"
                Notification.show("Categoria Selecionada.", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        });

        buttonSalvar.addClickListener(event -> {
            try {
                String descricao = textFieldDescricao.getValue().trim();

                // Verifica se a descrição não está vazia
                if (!descricao.isEmpty()) {
                    // Valida se o ID é um inteiro
                    try {
                        // int id = Integer.parseInt(textFieldID.getValue());

                        // Salva a categoria e verifica se a operação foi bem-sucedida
                        if (categoriaTarefaController.salvarCategoriaTarefa(descricao)) {
                            // Notificação de sucesso
                            Notification notification = new Notification("Categoria salva com sucesso.", 3000);
                            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                            notification.setPosition(Notification.Position.MIDDLE);
                            notification.open();

                            // Limpa os campos de texto
                            textFieldID.clear();
                            textFieldDescricao.clear();

                            // Define o foco no campo ID
                            textFieldID.focus();

                            // Atualiza o grid de consulta
                            atualizarGridConsulta();
                        } else {
                            // Notificação de erro ao salvar
                            Notification notification = new Notification("Erro ao salvar a categoria.", 3000);
                            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                            notification.setPosition(Notification.Position.MIDDLE);
                            notification.open();
                        }
                    } catch (NumberFormatException e) {
                        // Notificação para ID inválido
                        Notification notification = new Notification("ID deve ser um número inteiro.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    // Notificação para preencher todos os campos
                    Notification notification = new Notification(
                            "Por favor, preencha todos os campos corretamente. Descrição não pode conter apenas espaços em branco.",
                            3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } catch (Exception e) {
                // Notificação de erro genérico
                Notification notification = new Notification("Ocorreu um erro inesperado. Por favor, tente novamente.",
                        3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });

        buttonAlterar.addClickListener(event -> {
            String idText = textFieldID.getValue();
            String descricao = textFieldDescricao.getValue().trim();

            // Verifica se o ID e a descrição não estão vazios
            if (!idText.isEmpty() && !descricao.isEmpty()) {
                try {
                    int id = Integer.parseInt(idText);

                    // Cria uma mensagem de confirmação
                    ConfirmDialog dialog = new ConfirmDialog(
                            "Confirmar Alteração",
                            "Tem certeza que deseja alterar a categoria da tarefa?",
                            "Confirmar",
                            eventConfirm -> {
                                // Atualiza a prioridade e verifica se a operação foi bem-sucedida
                                if (categoriaTarefaController.alterarCategoriaTarefa(id, descricao)) {
                                    // Notificação de sucesso
                                    Notification notification = new Notification("Categoria alterada com sucesso.",
                                            3000);
                                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                                    notification.setPosition(Notification.Position.MIDDLE);
                                    notification.open();

                                    // Limpa os campos de texto
                                    textFieldID.clear();
                                    textFieldDescricao.clear();

                                    // Define o foco no campo ID
                                    textFieldID.focus();

                                    // Atualiza o grid de consulta
                                    atualizarGridConsulta();
                                } else {
                                    // Notificação de erro ao alterar
                                    Notification notification = new Notification("Erro ao alterar a categoria.", 3000);
                                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                                    notification.setPosition(Notification.Position.MIDDLE);
                                    notification.open();
                                }
                            },
                            "Cancelar",
                            eventCancel -> {
                                // Cancela a ação
                                Notification notification = new Notification("Alteração cancelada.", 3000);
                                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                                notification.setPosition(Notification.Position.MIDDLE);
                                notification.open();
                            });

                    // Abre o diálogo de confirmação
                    dialog.open();
                } catch (NumberFormatException e) {
                    // Notificação para ID inválido
                    Notification notification = new Notification("ID deve ser um número inteiro.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } else {
                // Notificação para preencher todos os campos
                Notification notification = new Notification(
                        "Por favor, preencha todos os campos corretamente. Descrição não pode conter apenas espaços em branco.",
                        3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });

        buttonExcluir.addClickListener(event -> {
            String idText = textFieldID.getValue();

            // Verifica se o ID não está vazio
            if (!idText.isEmpty()) {
                int id = Integer.parseInt(idText);

                // Cria uma mensagem de confirmação
                ConfirmDialog dialog = new ConfirmDialog(
                        "Confirmar Exclusão",
                        "Tem certeza que deseja excluir a categoria?",
                        "Confirmar",
                        eventConfirm -> {
                            // Exclui a prioridade e verifica se a operação foi bem-sucedida
                            if (categoriaTarefaController.deletarCategoriaTarefa(id)) {
                                // Notificação de sucesso
                                Notification notification = new Notification("Categoria excluída com sucesso.", 3000);
                                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                                notification.setPosition(Notification.Position.MIDDLE);
                                notification.open();

                                // Limpa os campos de texto
                                textFieldID.clear();
                                textFieldDescricao.clear();

                                // Define o foco no campo ID
                                textFieldID.focus();

                                // Atualiza o grid de consulta
                                atualizarGridConsulta();
                            } else {
                                // Notificação de erro ao excluir
                                Notification notification = new Notification("Categoria não encontrada!", 3000);
                                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                                notification.setPosition(Notification.Position.MIDDLE);
                                notification.open();
                            }
                        },
                        "Cancelar",
                        eventCancel -> {
                            // Cancela a ação
                            Notification notification = new Notification("Exclusão cancelada.", 3000);
                            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                            notification.setPosition(Notification.Position.MIDDLE);
                            notification.open();
                        });

                // Abre o diálogo de confirmação
                dialog.open();
            } else {
                // Notificação para preencher o campo ID
                Notification notification = new Notification("Por favor, preencha o campo ID.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });

        buttonPesquisar.addClickListener(event -> {
            String idText = textFieldID.getValue();

            // Verifica se o ID não está vazio
            if (!idText.isEmpty()) {
                int id = Integer.parseInt(idText);

                // Busca a prioridade pelo ID
                CategoriaTarefa categoriaTarefa = categoriaTarefaController.buscarCategoriaTarefa(id);

                // Verifica se a prioridade foi encontrada
                if (categoriaTarefa != null) {
                    // Preenche o campo de descrição com as informações da prioridade encontrada
                    textFieldDescricao.setValue(categoriaTarefa.getDescricao());

                    // Notificação de sucesso
                    Notification notification = new Notification("Categoria encontrada.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    textFieldDescricao.focus();
                } else {
                    // Limpa o campo de descrição
                    textFieldDescricao.clear();
                    textFieldID.focus();

                    // Notificação de erro ao não encontrar a prioridade
                    Notification notification = new Notification("Categoria não encontrada.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } else {
                // Notificação para preencher o campo ID
                Notification notification = new Notification("Por favor, preencha o campo ID.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });

    }

    private void atualizarGridConsulta() {
        List<CategoriaTarefa> atualizados = categoriaTarefaController.buscarTodasCategoriasTarefa();
        if (atualizados != null && !atualizados.isEmpty()) {
            gridConsulta.setItems(atualizados);
        } else {
            gridConsulta.setItems(Collections.emptyList()); // ou outra lista vazia adequada
        }
    }
}
