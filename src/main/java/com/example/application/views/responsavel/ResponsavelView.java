package com.example.application.views.responsavel;

import java.util.Collections;
import java.util.List;

import com.example.application.controller.ResponsavelController;
import com.example.application.models.Responsavel;
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

@PageTitle("Responsavel")
@Route(value = "responsavel", layout = MainLayout.class)
@RouteAlias(value = "responsavel", layout = MainLayout.class)
public class ResponsavelView extends Composite<VerticalLayout> {

    private ResponsavelController responsavelController;
    private Grid<Responsavel> gridConsulta;

    public ResponsavelView() {
        responsavelController = new ResponsavelController();

        Tab tabGerenciar = new Tab("Gerenciar Responsável");
        Tab tabConsultar = new Tab("Consultar Responsável");
        Tabs tabs = new Tabs(tabGerenciar, tabConsultar);

        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textFieldID = new TextField();
        TextField textFieldNome = new TextField();
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
        h3.setText("Gerenciar Responsável");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textFieldID.setLabel("ID");
        textFieldNome.setLabel("Nome");
        textFieldNome.setWidth("56%");
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
        formLayout2Col.add(textFieldNome);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonSalvar);
        layoutRow.add(buttonAlterar);
        layoutRow.add(buttonExcluir);
        layoutRow.add(buttonPesquisar);

        VerticalLayout layoutConsulta = new VerticalLayout();
        H3 h3Consulta = new H3("Consultar Responsável");
        gridConsulta = new Grid<>(Responsavel.class);
        layoutConsulta.add(h3Consulta, gridConsulta);
        layoutConsulta.setVisible(false);

        getContent().add(tabs, layoutColumn2, layoutConsulta);
        layoutColumn2.add(h3, textFieldID, textFieldNome, layoutRow);
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
            Responsavel responsavel = event.getItem();
            if (responsavel != null) {
                textFieldID.setValue(String.valueOf(responsavel.getId()));
                textFieldNome.setValue(responsavel.getNome());
                tabs.setSelectedTab(tabGerenciar); // Alterna para a aba "Gerenciar Responsavel"
                Notification.show("Responsável selecionado.", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        });

        buttonSalvar.addClickListener(event -> {
            try {
                String nome = textFieldNome.getValue().trim();

                // Verifica se o nome não está vazio
                if (!nome.isEmpty()) {
                    // Valida se o ID é um inteiro
                    try {
                        // int id = Integer.parseInt(textFieldID.getValue());

                        // Salva a prioridade e verifica se a operação foi bem-sucedida
                        if (responsavelController.salvarResponsavel(nome)) {
                            // Notificação de sucesso
                            Notification notification = new Notification("Responsável salvo com sucesso.", 3000);
                            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                            notification.setPosition(Notification.Position.MIDDLE);
                            notification.open();

                            // Limpa os campos de texto
                            textFieldID.clear();
                            textFieldNome.clear();

                            // Define o foco no campo ID
                            textFieldID.focus();

                            // Atualiza o grid de consulta
                            atualizarGridConsulta();
                        } else {
                            // Notificação de erro ao salvar
                            Notification notification = new Notification("Erro ao salvar o responsável.", 3000);
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
                            "Por favor, preencha todos os campos corretamente. Nome não pode conter apenas espaços em branco.",
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
            String nome = textFieldNome.getValue().trim();

            if (!idText.isEmpty() && !nome.isEmpty()) {
                try {
                    int id = Integer.parseInt(idText);

                    // Cria uma mensagem de confirmação
                    ConfirmDialog dialog = new ConfirmDialog(
                            "Confirmar Alteração",
                            "Tem certeza que deseja alterar o responsável?",
                            "Confirmar",
                            eventConfirm -> {
                                // Atualiza o responsável e verifica se a operação foi bem-sucedida
                                if (responsavelController.alterarResponsavel(id, nome)) {
                                    // Notificação de sucesso
                                    Notification notification = new Notification("Responsável alterado com sucesso.",
                                            3000);
                                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                                    notification.setPosition(Notification.Position.MIDDLE);
                                    notification.open();

                                    // Limpa os campos de texto
                                    textFieldID.clear();
                                    textFieldNome.clear();

                                    // Define o foco no campo ID
                                    textFieldID.focus();

                                    // Atualiza o grid de consulta
                                    atualizarGridConsulta();
                                } else {
                                    // Notificação de erro ao alterar
                                    Notification notification = new Notification("Erro ao alterar o responsável.",
                                            3000);
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
                        "Por favor, preencha todos os campos corretamente. Nome não pode conter apenas espaços em branco.",
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
                        "Tem certeza que deseja excluir Responsável?",
                        "Confirmar",
                        eventConfirm -> {
                            // Exclui a responsável e verifica se a operação foi bem-sucedida
                            if (responsavelController.deletarResponsavel(id)) {
                                // Notificação de sucesso
                                Notification notification = new Notification("Responsável excluído com sucesso.", 3000);
                                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                                notification.setPosition(Notification.Position.MIDDLE);
                                notification.open();

                                // Limpa os campos de texto
                                textFieldID.clear();
                                textFieldNome.clear();

                                // Define o foco no campo ID
                                textFieldID.focus();

                                // Atualiza o grid de consulta
                                atualizarGridConsulta();
                            } else {
                                // Notificação de erro ao excluir
                                Notification notification = new Notification("Responsável não encontrado!", 3000);
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

                // Busca o responsável pelo ID
                Responsavel responsavel = responsavelController.buscarResponsavel(id);

                // Verifica se a responsável foi encontrada
                if (responsavel != null) {
                    // Preenche o campo de descrição com as informações da prioridade encontrada
                    textFieldNome.setValue(responsavel.getNome());

                    // Notificação de sucesso
                    Notification notification = new Notification("Prioridade encontrada.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    textFieldNome.focus();
                } else {
                    // Limpa o campo de descrição
                    textFieldNome.clear();
                    textFieldID.focus();

                    // Notificação de erro ao não encontrar a prioridade
                    Notification notification = new Notification("Prioridade não encontrada.", 3000);
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
        List<Responsavel> atualizados = responsavelController.buscarTodosResponsaveis();
        if (atualizados != null && !atualizados.isEmpty()) {
            gridConsulta.setItems(atualizados);
        } else {
            gridConsulta.setItems(Collections.emptyList()); // ou outra lista vazia adequada
        }
    }
}
