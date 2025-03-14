package khouya.site.tp2;

import khouya.site.tp2.entities.Patient;
import khouya.site.tp2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Tp2Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);
    }

    @Autowired
    PatientRepository patientRepository;

    @Bean
    CommandLineRunner runner() {

        return args -> {
            // Ajouter des patients avec le Builder
            patientRepository.save(Patient.builder()
                    .nom("Ahmed")
                    .dateNaissance(new Date())
                    .malade(false)
                    .score(15)
                    .build());

            patientRepository.save(Patient.builder()
                    .nom("Fatima")
                    .dateNaissance(new Date())
                    .malade(true)
                    .score(8)
                    .build());

            patientRepository.save(Patient.builder()
                    .nom("Omar")
                    .dateNaissance(new Date())
                    .malade(false)
                    .score(12)
                    .build());

            patientRepository.save(Patient.builder()
                    .nom("Jamila")
                    .dateNaissance(new Date())
                    .malade(true)
                    .score(15)
                    .build());

            // Consulter tous les patients
            System.out.println("\nListe des patients:");
            patientRepository.findAll().forEach(System.out::println);

            // Consulter un patient par ID
            Patient patient = patientRepository.findById(1L).orElse(null);
            System.out.println("\n Patient avec ID 1: " + patient);

            // Chercher des patients malades
            System.out.println("\n List des Patients malades:");
            patientRepository.findByMalade(true).forEach(System.out::println);

            // Mettre à jour un patient 1
            System.out.println("\n Mettre à jour le score de patient 1");
            if (patient != null) {
                patient.setScore(20);
                patientRepository.save(patient);
                System.out.println("Patient mis à jour: " + patient);
            }

            // Supprimer un patient
            patientRepository.deleteById(2L);
            System.out.println("\nPatient avec ID 2 supprimé.");

            // Consulter tous les patients
            System.out.println("\nListe des patients:");
            patientRepository.findAll().forEach(System.out::println);
        };
    }
}
