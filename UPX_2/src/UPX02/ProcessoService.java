package UPX02;

import java.sql.Connection; // Importando a classe Connection para gerenciar a conexão com o banco
import java.sql.PreparedStatement; // Para preparar comandos SQL
import java.sql.ResultSet; // Para trabalhar com o resultado de consultas SQL

import dao.DatabaseConnection;

public class ProcessoService {
    // Método para obter detalhes de um processo com base no número do protocolo
    public String obterDetalhesDoProcesso(String numeroProtocolo) {
        // Consulta SQL para obter detalhes do processo
        String query = "SELECT p.numero_protocolo, pf.nome AS prefeitura, p.data_abertura, tp.descricao AS tipo_processo, " +
                       "r.nome AS requerente, p.situacao, p.ultima_atualizacao, p.descricao AS descricao_processo, " +
                       "p.observacoes, p.documentos_faltantes, p.aprovado_por, p.data_vencimento, p.historico, " +
                       "p.prazo_estimado_resposta " +
                       "FROM Processos p " +
                       "JOIN Prefeituras pf ON p.id_prefeitura = pf.id_prefeitura " +
                       "JOIN Requerentes r ON p.id_requerente = r.id_requerente " +
                       "JOIN Tipos_Processos tp ON p.id_tipo_processo = tp.id_tipo_processo " +
                       "WHERE p.numero_protocolo = ?"; // A interrogação (?) é um placeholder para o parâmetro

        StringBuilder detalhes = new StringBuilder(); // Usado para construir a string de detalhes do processo

        // Tentativa de conexão com o banco de dados e execução da consulta
        try (Connection connection = DatabaseConnection.getConnection(); // Obtendo a conexão
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { // Preparando a consulta

            // Definindo o número do protocolo na consulta
            preparedStatement.setString(1, numeroProtocolo); 

            // Executando a consulta e armazenando o resultado
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Verifica se há resultados na consulta
                if (resultSet.next()) {
                    // Montando a string com os detalhes do processo
                    detalhes.append("Detalhes do Processo:\n");
                    detalhes.append("--------------------------------------------------\n");
                    detalhes.append("Número do Protocolo: ").append(resultSet.getString("numero_protocolo")).append("\n");
                    detalhes.append("Prefeitura: ").append(resultSet.getString("prefeitura")).append("\n");
                    detalhes.append("Data de Abertura: ").append(resultSet.getDate("data_abertura")).append("\n");
                    detalhes.append("Tipo de Processo: ").append(resultSet.getString("tipo_processo")).append("\n");
                    detalhes.append("Requerente: ").append(resultSet.getString("requerente")).append("\n");
                    detalhes.append("Situação: ").append(resultSet.getString("situacao")).append("\n");
                    detalhes.append("Última Atualização: ").append(resultSet.getTimestamp("ultima_atualizacao")).append("\n");
                    detalhes.append("Descrição: ").append(resultSet.getString("descricao_processo")).append("\n");
                    detalhes.append("Observações: ").append(resultSet.getString("observacoes")).append("\n");
                    detalhes.append("Documentos Faltantes: ").append(resultSet.getString("documentos_faltantes")).append("\n");
                    detalhes.append("Aprovado por: ").append(resultSet.getString("aprovado_por")).append("\n");
                    detalhes.append("Data de Vencimento: ").append(resultSet.getDate("data_vencimento")).append("\n");
                    detalhes.append("Histórico: ").append(resultSet.getString("historico")).append("\n");
                    detalhes.append("Prazo Estimado para Resposta: ").append(resultSet.getInt("prazo_estimado_resposta")).append(" dias\n");
                    detalhes.append("--------------------------------------------------\n");
                } else {
                    // Caso não encontre nenhum processo com o número de protocolo fornecido
                    detalhes.append("Nenhum processo encontrado com o número de protocolo: ").append(numeroProtocolo).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace em caso de erro
            detalhes.append("Erro ao consultar o processo: ").append(e.getMessage()).append("\n");
        }

        return detalhes.toString(); // Retorna os detalhes do processo
    }
}
