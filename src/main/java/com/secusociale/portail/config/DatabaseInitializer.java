package com.secusociale.portail.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final DataSource dataSource;

    @Autowired
    public DatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                if (checkColumnExists(connection, "salarie_tp", "statut")) {
                    statement.execute("ALTER TABLE salarie_tp MODIFY COLUMN statut ENUM('VALIDE', 'REJETE', 'SOUMIS', 'SAISIE', 'RETOURNE') NOT NULL;");
                }
                if(checkColumnExists(connection,"carriere_manquantes","nom_employeur")){
                    statement.execute("ALTER TABLE carriere_manquantes MODIFY COLUMN nom_employeur VARCHAR(255) DEFAULT '';");
                }
                if(checkColumnExists(connection,"reclamation","statut")){
                    statement.execute("UPDATE reclamation SET statut = 'VALIDATION_CHEF_AGENCE_CSS' WHERE statut = 'VALIDATION_CHEF_AGENCE';");
                    statement.execute("UPDATE reclamation SET statut = 'REJETE' WHERE statut = 'REJETTE';");
                }
                NowItIsNullable(connection, "temps_de_presence_heure_mois_1","salarie_tp","INT");
                NowItIsNullable(connection, "temps_de_presence_heure_mois_2","salarie_tp","INT");
                NowItIsNullable(connection, "temps_de_presence_heure_mois_3","salarie_tp","INT");
                NowItIsNullable(connection, "age","salarie_tp","INT");
                NowItIsNullable(connection, "nombre_enfant_eligibre","salarie_tp","INT");
                NowItIsNullable(connection, "numero_assure_social","salarie_tp","INT");
                NowItIsNullable(connection, "trimestre","contenu_tp","INT");
                NowItIsNullable(connection, "numero_unique","carriere_manquantes","INT");
                NowItIsNullable(connection, "adresse","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "agence_css","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "agence_ipres","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "ancien_num_css","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "ancien_num_ipres","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "effectif","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "numero_unique","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "raison_sociale","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "rccm","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "region","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "secteur_activite","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "statut_juridique","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "telephone","declared_employer","VARCHAR(255)");
                NowItIsNullable(connection, "agence_id","reclamation","VARCHAR(255)");
            }
        } catch (SQLException e) {
            logger.error("Erreur lors de la vérification ou modification d'une colonne dans la class DatabaseInitializer : ", e);
        }
    }

    private void NowItIsNullable(Connection connection, String columnName, String tableName,String columnType) throws SQLException {

        if (checkColumnExists(connection, tableName, columnName)) {

            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format(
                    "ALTER TABLE %s MODIFY COLUMN %s %s NULL;", tableName, columnName,columnType
                ));
            }
        } else {
            logger.warn("La colonne {} n'existe pas dans la table {}.", columnName, tableName);
        }
    }

    private boolean checkColumnExists(Connection connection, String tableName, String columnName) throws SQLException {
        boolean columnExists = false;
        DatabaseMetaData metaData = connection.getMetaData();

        String schema = connection.getCatalog();

        try (ResultSet resultSet = metaData.getColumns(schema, null, tableName, columnName)) {
            columnExists = resultSet.next();
            logger.debug("Vérification de la colonne : schema={}, table={}, colonne={}, existe={}",
                schema, tableName, columnName, columnExists);
        }

        return columnExists;
    }
}
