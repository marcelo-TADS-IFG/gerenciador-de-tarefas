package com.example.application.views.listadetarefas;

import com.example.application.controller.ListaTarefasController;
import com.example.application.models.ListaTarefas;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.example.application.controller.PrioridadeController;
import com.example.application.models.Prioridade;
import com.example.application.controller.ResponsavelController;
import com.example.application.models.Responsavel;
import com.example.application.controller.CategoriaTarefaController;
import com.example.application.models.CategoriaTarefa;
import com.example.application.controller.StatusController;
import com.example.application.models.Status;

@PageTitle("Lista de Tarefas")
@Route(value = "Lista-De-Tarefas", layout = MainLayout.class)
@RouteAlias(value = "Lista-De-Tarefas", layout = MainLayout.class)
public class ListadeTarefasView extends Composite<VerticalLayout> {

    private ListaTarefasController listaTarefasController;
    private Grid<ListaTarefas> gridConsulta;
    private PrioridadeController prioridadeController;
    private ResponsavelController responsavelController;
    private CategoriaTarefaController categoriaTarefaController;
    private StatusController statusController;

    private ComboBox<Prioridade> comboBoxPrioridade;
    private ComboBox<Responsavel> comboBoxResponsavel;
    private ComboBox<CategoriaTarefa> comboBoxCategoriaTarefa;
    private ComboBox<Status> comboBoxStatus;

    public ListadeTarefasView() {
        listaTarefasController = new ListaTarefasController();
        prioridadeController = new PrioridadeController();
        responsavelController = new ResponsavelController();
        categoriaTarefaController = new CategoriaTarefaController();
        statusController = new StatusController();

        Tab tabGerenciar = new Tab("Gerenciar Lista de Tarefas");
        Tab tabConsultar = new Tab("Consultar Lista de Tarefas");
        Tabs tabs = new Tabs(tabGerenciar, tabConsultar);

        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textFieldObservacao = new TextField();
        DateTimePicker dateTimePicker = new DateTimePicker();
        TextField textFieldDescricao = new TextField();
        TextField textFieldID = new TextField();
        comboBoxPrioridade = new ComboBox<>();
        comboBoxResponsavel = new ComboBox<>();
        comboBoxCategoriaTarefa = new ComboBox<>();
        comboBoxStatus = new ComboBox<>();
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
        h3.setText("Gerenciar Tarefas");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textFieldID.setLabel("ID");
        dateTimePicker.setLabel("Selecione Data e Hora");
        textFieldDescricao.setLabel("Descrição");
        textFieldDescricao.setWidth("100%");
        textFieldObservacao.setLabel("Observação");
        comboBoxPrioridade.setLabel("Prioridade");
        comboBoxPrioridade.setWidth("min-content");
        setComboBoxPrioridades(comboBoxPrioridade);
        comboBoxResponsavel.setLabel("Responsável");
        comboBoxResponsavel.setWidth("min-content");
        setComboBoxResponsaveis(comboBoxResponsavel);
        comboBoxCategoriaTarefa.setLabel("Categoria Tarefa");
        comboBoxCategoriaTarefa.setWidth("min-content");
        setComboBoxCategoriaTarefas(comboBoxCategoriaTarefa);
        comboBoxStatus.setLabel("Status");
        comboBoxStatus.setWidth("min-content");
        setComboBoxStatus(comboBoxStatus);
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
        formLayout2Col.add(textFieldObservacao);
        formLayout2Col.add(dateTimePicker);
        formLayout2Col.add(comboBoxPrioridade);
        formLayout2Col.add(comboBoxResponsavel);
        formLayout2Col.add(comboBoxCategoriaTarefa);
        formLayout2Col.add(comboBoxStatus);
        layoutColumn2.add(layoutRow);
        layoutRow.add(buttonSalvar);
        layoutRow.add(buttonAlterar);
        layoutRow.add(buttonExcluir);
        layoutRow.add(buttonPesquisar);

        VerticalLayout layoutConsulta = new VerticalLayout();
        H3 h3Consulta = new H3("Consultar Lista de Tarefa");
        gridConsulta = new Grid<>(ListaTarefas.class);
        layoutConsulta.add(h3Consulta, gridConsulta);
        layoutConsulta.setVisible(false);

        getContent().add(tabs, layoutColumn2, layoutConsulta);
        layoutColumn2.add(layoutRow);
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
            ListaTarefas listaTarefas = event.getItem();
            if (listaTarefas != null) {
                textFieldID.setValue(String.valueOf(listaTarefas.getId()));
                textFieldDescricao.setValue(listaTarefas.getDescricaoTarefa());
                textFieldObservacao.setValue(listaTarefas.getObservacao());
                dateTimePicker.setValue(listaTarefas.getDataTarefa());
                comboBoxPrioridade.setValue(listaTarefas.getPrioridade());
                comboBoxResponsavel.setValue(listaTarefas.getResponsavel());
                comboBoxCategoriaTarefa.setValue(listaTarefas.getCategoriaTarefa());
                comboBoxStatus.setValue(listaTarefas.getStatus());
                tabs.setSelectedTab(tabGerenciar); // Alterna para a aba "Gerenciar Tarefas"
                Notification.show("Tarefa Selecionada.", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        });

        buttonSalvar.addClickListener(event -> {
            try {
                String descricao = textFieldDescricao.getValue().trim();
                String observacao = textFieldObservacao.getValue().trim();
                LocalDateTime dataHora = dateTimePicker.getValue();
                Prioridade prioridade = comboBoxPrioridade.getValue();
                CategoriaTarefa categoriaTarefa = comboBoxCategoriaTarefa.getValue();
                Status status = comboBoxStatus.getValue();
                Responsavel responsavel = comboBoxResponsavel.getValue();

                if (!descricao.isEmpty() && dataHora != null && prioridade != null && categoriaTarefa != null
                        && status != null && responsavel != null) {
                    if (listaTarefasController.salvarTarefa(dataHora, descricao, observacao, prioridade,
                            categoriaTarefa, status, responsavel)) {
                        Notification notification = new Notification("Tarefa salva com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();

                        textFieldID.clear();
                        textFieldDescricao.clear();
                        textFieldObservacao.clear();
                        dateTimePicker.clear();
                        comboBoxPrioridade.clear();
                        comboBoxCategoriaTarefa.clear();
                        comboBoxStatus.clear();
                        comboBoxResponsavel.clear();

                        textFieldID.focus();

                        atualizarGridConsulta();
                    } else {
                        Notification notification = new Notification("Erro ao salvar a tarefa.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    Notification notification = new Notification("Por favor, preencha todos os campos corretamente.",
                            3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } catch (Exception e) {
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
            String observacao = textFieldObservacao.getValue().trim();
            LocalDateTime dataHora = dateTimePicker.getValue();
            Prioridade prioridade = comboBoxPrioridade.getValue();
            CategoriaTarefa categoriaTarefa = comboBoxCategoriaTarefa.getValue();
            Status status = comboBoxStatus.getValue();
            Responsavel responsavel = comboBoxResponsavel.getValue();

            // Verifica se o ID e a descrição não estão vazios e se os outros campos
            // necessários estão preenchidos
            if (!idText.isEmpty() && !descricao.isEmpty() && dataHora != null && prioridade != null
                    && categoriaTarefa != null && status != null && responsavel != null) {
                try {
                    int id = Integer.parseInt(idText);

                    // Cria uma mensagem de confirmação
                    ConfirmDialog dialog = new ConfirmDialog(
                            "Confirmar Alteração",
                            "Tem certeza que deseja alterar a tarefa?",
                            "Confirmar",
                            eventConfirm -> {
                                // Atualiza a tarefa e verifica se a operação foi bem-sucedida
                                if (listaTarefasController.alterarTarefa(id, dataHora, descricao, observacao,
                                        prioridade, categoriaTarefa, status, responsavel)) {
                                    // Notificação de sucesso
                                    Notification notification = new Notification("Tarefa alterada com sucesso.", 3000);
                                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                                    notification.setPosition(Notification.Position.MIDDLE);
                                    notification.open();

                                    // Limpa os campos de texto
                                    textFieldID.clear();
                                    textFieldDescricao.clear();
                                    textFieldObservacao.clear();
                                    dateTimePicker.clear();
                                    comboBoxPrioridade.clear();
                                    comboBoxCategoriaTarefa.clear();
                                    comboBoxStatus.clear();
                                    comboBoxResponsavel.clear();

                                    // Define o foco no campo ID
                                    textFieldID.focus();

                                    // Atualiza o grid de consulta
                                    atualizarGridConsulta();
                                } else {
                                    // Notificação de erro ao alterar
                                    Notification notification = new Notification("Tarefa não encontrada.", 3000);
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
                        "Por favor, preencha todos os campos corretamente.",
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
                try {
                    int id = Integer.parseInt(idText);

                    // Cria uma mensagem de confirmação
                    ConfirmDialog dialog = new ConfirmDialog(
                            "Confirmar Exclusão",
                            "Tem certeza que deseja excluir a tarefa?",
                            "Excluir",
                            eventConfirm -> {
                                // Exclui a tarefa e verifica se a operação foi bem-sucedida
                                if (listaTarefasController.deletarTarefa(id)) {
                                    // Notificação de sucesso
                                    Notification notification = new Notification("Tarefa excluída com sucesso.", 3000);
                                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                                    notification.setPosition(Notification.Position.MIDDLE);
                                    notification.open();

                                    // Limpa os campos de texto
                                    textFieldID.clear();
                                    textFieldDescricao.clear();
                                    textFieldObservacao.clear();
                                    dateTimePicker.clear();
                                    comboBoxPrioridade.clear();
                                    comboBoxCategoriaTarefa.clear();
                                    comboBoxStatus.clear();
                                    comboBoxResponsavel.clear();

                                    // Define o foco no campo ID
                                    textFieldID.focus();

                                    // Atualiza o grid de consulta
                                    atualizarGridConsulta();
                                } else {
                                    // Notificação de erro ao excluir
                                    Notification notification = new Notification("Tarefa não encontrada.", 3000);
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
                } catch (NumberFormatException e) {
                    // Notificação para ID inválido
                    Notification notification = new Notification("ID deve ser um número inteiro.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } else {
                // Notificação para preencher o campo ID
                Notification notification = new Notification("Por favor, informe o ID da tarefa.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });

        buttonPesquisar.addClickListener(event -> {
            String idText = textFieldID.getValue();

            // Verifica se o ID não está vazio
            if (!idText.isEmpty()) {
                try {
                    int id = Integer.parseInt(idText);

                    // Busca a tarefa pelo ID
                    ListaTarefas tarefa = listaTarefasController.buscarTarefa(id);

                    // Verifica se a tarefa foi encontrada
                    if (tarefa != null) {
                        // Preenche os campos com as informações da tarefa encontrada
                        textFieldDescricao.setValue(tarefa.getDescricaoTarefa());
                        textFieldObservacao.setValue(tarefa.getObservacao());
                        dateTimePicker.setValue(tarefa.getDataTarefa());
                        comboBoxPrioridade.setValue(tarefa.getPrioridade());
                        comboBoxCategoriaTarefa.setValue(tarefa.getCategoriaTarefa());
                        comboBoxStatus.setValue(tarefa.getStatus());
                        comboBoxResponsavel.setValue(tarefa.getResponsavel());

                        // Notificação de sucesso
                        Notification notification = new Notification("Tarefa encontrada.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();

                        textFieldDescricao.focus();
                    } else {
                        // Limpa os campos
                        textFieldDescricao.clear();
                        textFieldObservacao.clear();
                        dateTimePicker.clear();
                        comboBoxPrioridade.clear();
                        comboBoxCategoriaTarefa.clear();
                        comboBoxStatus.clear();
                        comboBoxResponsavel.clear();
                        textFieldID.focus();

                        // Notificação de erro ao não encontrar a tarefa
                        Notification notification = new Notification("Tarefa não encontrada.", 3000);
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
                // Notificação para preencher o campo ID
                Notification notification = new Notification("Por favor, preencha o campo ID.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            }
        });

    }

    private void setComboBoxPrioridades(ComboBox<Prioridade> comboBox) {
        List<Prioridade> prioridades = prioridadeController.buscarTodasPrioridades();
        comboBox.setItems(prioridades);
        comboBox.setItemLabelGenerator(Prioridade::getDescricao);
    }

    private void setComboBoxResponsaveis(ComboBox<Responsavel> comboBox) {
        List<Responsavel> responsaveis = responsavelController.buscarTodosResponsaveis();
        comboBox.setItems(responsaveis);
        comboBox.setItemLabelGenerator(Responsavel::getNome);
    }

    private void setComboBoxCategoriaTarefas(ComboBox<CategoriaTarefa> comboBox) {
        List<CategoriaTarefa> categorias = categoriaTarefaController.buscarTodasCategoriasTarefa();
        comboBox.setItems(categorias);
        comboBox.setItemLabelGenerator(CategoriaTarefa::getDescricao);
    }

    private void setComboBoxStatus(ComboBox<Status> comboBox) {
        List<Status> statusList = statusController.buscarTodosStatus();
        comboBox.setItems(statusList);
        comboBox.setItemLabelGenerator(Status::getDescricao);
    }

    private void atualizarGridConsulta() {
        List<ListaTarefas> tarefas = listaTarefasController.buscarTodasTarefas();
        gridConsulta.setItems(tarefas);
        gridConsulta.removeAllColumns();
        gridConsulta.addColumn(ListaTarefas::getId).setHeader("ID");
        gridConsulta.addColumn(ListaTarefas::getDescricaoTarefa).setHeader("Descrição");
        gridConsulta.addColumn(ListaTarefas::getObservacao).setHeader("Observação");
        gridConsulta.addColumn(ListaTarefas::getDataTarefa).setHeader("Data/Hora");
        gridConsulta.addColumn(listaTarefas -> listaTarefas.getPrioridade().getDescricao()).setHeader("Prioridade");
        gridConsulta.addColumn(listaTarefas -> listaTarefas.getResponsavel().getNome()).setHeader("Responsável");
        gridConsulta.addColumn(listaTarefas -> listaTarefas.getCategoriaTarefa().getDescricao()).setHeader("Categoria");
        gridConsulta.addColumn(listaTarefas -> listaTarefas.getStatus().getDescricao()).setHeader("Status");
    }

    /*private void atualizarGridConsulta() {
        List<ListaTarefas> atualizados = listaTarefasController.buscarTodasTarefas();
        if (atualizados != null && !atualizados.isEmpty()) {
            gridConsulta.setItems(atualizados);
        } else {
            gridConsulta.setItems(Collections.emptyList()); // ou outra lista vazia adequada
        }
    }*/

}