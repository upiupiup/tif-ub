package projek_akhir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GUIGame extends javax.swing.JFrame {
    private ButtonGroup buttonGroup1;
    private BackgroundPane1 jPanel1;
    private HomeBase homeBase;
    private javax.swing.JToggleButton actionExitGame;
    private javax.swing.JToggleButton actionHelpGame;
    private javax.swing.JToggleButton actionPlayGame;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSplitPane jSplitPane1;

    private Player player;
    private Dungeon dungeon;
    private JLabel battleStatusLabel;
    private JTextArea battleLogTextArea;

    private JButton attackButton;
    private JButton specialAttackButton;
    private JButton elementalAttackButton;
    private JButton defendButton;
    private JButton useItemButton;
    private JButton runButton;

    private BufferedWriter battleLogWriter;
    private JPanel playerPanel;
    private JPanel enemyPanel;
    private JPanel battlePanel;
    private Path logFilePath;


    public GUIGame() {
        initComponents();
        homeBase = new HomeBase();
        
        logFilePath = Paths.get("C:", "Users","Aufii","Documents","Informatika","SEM 2","PBO","projek akhir", "progressGame.txt");
        createDirectoriesIfNotExist(logFilePath.getParent());
        
        try {
            battleLogWriter = new BufferedWriter(new FileWriter(logFilePath.toString(), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        playBackgroundMusic(System.getProperty("user.home") + "/Downloads/sound_homeawal.wav");
    }
    
    public void playSound(String soundFile) {
        try {
            File audioFile = new File(soundFile);
            if (!audioFile.exists()) {
                System.out.println("File audio tidak ditemukan: " + soundFile);
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            System.out.println("Memutar suara: " + soundFile);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void playBackgroundMusic(String musicFile) {
        try {
            File audioFile = new File(musicFile);
            if (!audioFile.exists()) {
                System.out.println("File musik tidak ditemukan: " + musicFile);
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("Memutar musik latar belakang: " + musicFile);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        buttonGroup1 = new ButtonGroup();
        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new BackgroundPane1(System.getProperty("user.home") + "/Downloads/pokemon22.jpg");
        jLabel1 = new javax.swing.JLabel();
        actionExitGame = new javax.swing.JToggleButton();
        actionPlayGame = new javax.swing.JToggleButton();
        actionHelpGame = new javax.swing.JToggleButton();

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        buttonGroup1.add(actionExitGame);
        buttonGroup1.add(actionPlayGame);
        buttonGroup1.add(actionHelpGame);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", Font.BOLD, 50));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setVerticalAlignment(SwingConstants.CENTER);
        jLabel1.setText("POKEMON UHUY");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        // Mengatur background panel untuk judul
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new java.awt.Color(70, 130, 180, 150)); // Warna lebih cerah dan semi-transparan
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); // Menyesuaikan panjang dengan teks
        titlePanel.setPreferredSize(new Dimension(jLabel1.getPreferredSize().width + 20, jLabel1.getPreferredSize().height + 20)); // Sesuaikan ukuran panel

        titlePanel.add(jLabel1);

        actionExitGame.setFont(new java.awt.Font("Tahoma", 1, 25));
        actionExitGame.setText("EXIT");
        actionExitGame.setPreferredSize(new Dimension(150, 50));
        actionExitGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                actionExitGameActionPerformed(evt);
            }
        });

        actionPlayGame.setFont(new java.awt.Font("Tahoma", 1, 25));
        actionPlayGame.setText("PLAY");
        actionPlayGame.setPreferredSize(new Dimension(150, 50));
        actionPlayGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                actionPlayGameActionPerformed(evt);
            }
        });

        actionHelpGame.setFont(new java.awt.Font("Tahoma", 1, 25));
        actionHelpGame.setText("HELP");
        actionHelpGame.setPreferredSize(new Dimension(150, 50));
        actionHelpGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                actionHelpGameActionPerformed(evt);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(actionPlayGame, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(actionHelpGame, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(actionExitGame, gbc);

        jPanel1.add(titlePanel, BorderLayout.NORTH);
        jPanel1.add(buttonPanel, BorderLayout.CENTER);

        // Mengatur judul menu
        jMenu1.setText("Homebase");
        jMenu1.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));
        jMenu1.setBackground(new java.awt.Color(70, 130, 180, 150)); // Warna lebih cerah dan semi-transparan
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Dungeon");
        jMenu2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));
        jMenu2.setBackground(new java.awt.Color(70, 130, 180, 150)); // Warna lebih cerah dan semi-transparan
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void actionPlayGameActionPerformed(java.awt.event.ActionEvent evt) {
        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(new ConcreteMonster("Ditto", 5, 48, Element.WIND, new WindFeature()));
        monsterList.add(new ConcreteMonster("Bulbasaur", 5, 58, Element.EARTH, new EarthFeature()));
        monsterList.add(new ConcreteMonster("Charmander", 5, 78, Element.FIRE, new FireFeature()));
        monsterList.add(new ConcreteMonster("Squirtle", 5, 79, Element.WATER, new WaterFeature()));
        monsterList.add(new ConcreteMonster("Pikachu", 5, 60, Element.FIRE, new FireFeature()));
        monsterList.add(new ConcreteMonster("Sandslash", 5, 70, Element.ICE, new IceFeature()));
        monsterList.add(new ConcreteMonster("Golem", 5, 80, Element.EARTH, new EarthFeature()));
        monsterList.add(new ConcreteMonster("Spheal", 5, 90, Element.WATER, new WaterFeature()));

        HomeBase homeBase = new HomeBase();
        player = new Player(monsterList, homeBase);

        logAction("Permainan dimulai. Silakan pilih aksi di Homebase.");
        JOptionPane.showMessageDialog(this, "Permainan dimulai. Silakan pilih aksi di Homebase.");
        showHomebase();
    }

    private void showHomebase() {
        player.restoreMonsters(); // Restore the original list of monsters before showing homebase

        JFrame homebaseFrame = new JFrame("Homebase");
        homebaseFrame.setSize(1366, 768);
        homebaseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackgroundPane1 homebasePanel = new BackgroundPane1(System.getProperty("user.home") + "/Downloads/pokemon Homebase.jpg");
        homebasePanel.setLayout(new BorderLayout());

        JLabel homebaseLabel = new JLabel("Homebase");
        homebaseLabel.setFont(new java.awt.Font("Tahoma", 1, 30));
        homebaseLabel.setForeground(new java.awt.Color(255, 255, 255));
        homebaseLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton lihatMonsterButton = new JButton("Monster's Info");
        JButton beliItemButton = new JButton("Buy Item");
        JButton gunakanItemButton = new JButton("Restore HP");
        JButton pergiKeDungeonButton = new JButton("Go to Dungeon");
        JButton levelUpButton = new JButton("Level Up");
        JButton evolveButton = new JButton("Evolve Monster");
        JButton keluarButton = new JButton("Exit");

        Dimension buttonSize = new Dimension(200, 50); // Set smaller button size

        lihatMonsterButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        lihatMonsterButton.setPreferredSize(buttonSize);
        beliItemButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        beliItemButton.setPreferredSize(buttonSize);
        gunakanItemButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        gunakanItemButton.setPreferredSize(buttonSize);
        pergiKeDungeonButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        pergiKeDungeonButton.setPreferredSize(buttonSize);
        levelUpButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        levelUpButton.setPreferredSize(buttonSize);
        evolveButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        evolveButton.setPreferredSize(buttonSize);
        keluarButton.setFont(new java.awt.Font("Tahoma", 1, 20));
        keluarButton.setPreferredSize(buttonSize);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout()); // Using GridBagLayout for better control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around buttons

        // First row (1 button)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(lihatMonsterButton, gbc);

        // Second row (2 buttons)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        buttonPanel.add(beliItemButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(gunakanItemButton, gbc);

        // Third row (2 buttons)
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(levelUpButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(evolveButton, gbc);

        // Fourth row (2 buttons)
        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(keluarButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(pergiKeDungeonButton, gbc);

        evolveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                List<Monster> monsters = player.getMonsters();
                String[] monsterNames = new String[monsters.size()];
                for (int i = 0; i < monsters.size(); i++) {
                    monsterNames[i] = monsters.get(i).getName();
                }

                String chosenMonsterName = (String) JOptionPane.showInputDialog(homebaseFrame,
                        "Pilih monster untuk evolusi:",
                        "Pilih Monster",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        monsterNames,
                        monsterNames[0]);

                if (chosenMonsterName != null) {
                    Monster chosenMonster = null;
                    for (Monster monster : monsters) {
                        if (monster.getName().equals(chosenMonsterName)) {
                            chosenMonster = monster;
                            break;
                        }
                    }

                    if (chosenMonster != null) {
                        List<Element> possibleEvolutions = homeBase.getPossibleEvolutions(chosenMonster.getElement());
                        String[] evolutionOptions = new String[possibleEvolutions.size()];
                        for (int i = 0; i < possibleEvolutions.size(); i++) {
                            evolutionOptions[i] = possibleEvolutions.get(i).toString();
                        }

                        String chosenEvolution = (String) JOptionPane.showInputDialog(homebaseFrame,
                                "Pilih evolusi untuk " + chosenMonster.getName() + ":",
                                "Pilih Evolusi",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                evolutionOptions,
                                evolutionOptions[0]);

                        if (chosenEvolution != null) {
                            Element newElement = Element.valueOf(chosenEvolution);
                            boolean evolutionSuccess = homeBase.evolveMonster(chosenMonster, newElement);
                            if (evolutionSuccess) {
                                String log = chosenMonster.getName() + " berhasil berevolusi menjadi elemen " + newElement + "!";
                                logAction(log);
                                JOptionPane.showMessageDialog(homebaseFrame, log);
                            } else {
                                String log = chosenMonster.getName() + " tidak memiliki EP yang cukup untuk berevolusi!";
                                logAction(log);
                                JOptionPane.showMessageDialog(homebaseFrame, log);
                            }
                        }
                    }
                }
            }
        });

        lihatMonsterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                StringBuilder monsterInfo = new StringBuilder("Monster yang Anda miliki:\n");
                for (Monster monster : player.getMonsters()) {
                    monsterInfo.append(monster.getName()).append(" - Level: ")
                            .append(monster.getLevel()).append(", HP: ")
                            .append(monster.getHealthPoints()).append(", EP: ")
                            .append(monster.getExperiencePoints()).append("\n");
                }
                logAction("Menampilkan informasi monster:\n" + monsterInfo.toString());
                JOptionPane.showMessageDialog(homebaseFrame, monsterInfo.toString());
            }
        });

        beliItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                List<Monster> monsters = player.getMonsters();
                String[] monsterNames = new String[monsters.size()];
                for (int i = 0; i < monsters.size(); i++) {
                    monsterNames[i] = monsters.get(i).getName();
                }

                String chosenMonsterName = (String) JOptionPane.showInputDialog(homebaseFrame,
                        "Pilih monster yang akan membeli item:",
                        "Pilih Monster",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        monsterNames,
                        monsterNames[0]);

                if (chosenMonsterName != null) {
                    Monster chosenMonster = null;
                    for (Monster monster : monsters) {
                        if (monster.getName().equals(chosenMonsterName)) {
                            chosenMonster = monster;
                            break;
                        }
                    }

                    if (chosenMonster != null) {
                        String[] items = {"Health Potion", "Fire Potion", "Water Potion", "Wind Potion", "Earth Potion", "Ice Potion"};
                        String chosenItem = (String) JOptionPane.showInputDialog(homebaseFrame,
                                "Pilih item untuk dibeli:",
                                "Beli Item",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                items,
                                items[0]);

                        if (chosenItem != null) {
                            boolean purchaseSuccess = player.buyItem(chosenMonster, chosenItem);
                            if (purchaseSuccess) {
                                String log = "Item " + chosenItem + " berhasil dibeli untuk " + chosenMonster.getName() + "!";
                                logAction(log);
                                JOptionPane.showMessageDialog(homebaseFrame, log);
                            } else {
                                String log = chosenMonster.getName() + " tidak memiliki EP yang cukup untuk membeli item " + chosenItem + "!";
                                logAction(log);
                                JOptionPane.showMessageDialog(homebaseFrame, log);
                            }
                        }
                    }
                }
            }
        });

        gunakanItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                player.healAllMonsters();
                logAction("Semua monster telah dipulihkan ke HP penuh!");
                JOptionPane.showMessageDialog(homebaseFrame, "Semua monster telah dipulihkan ke HP penuh!");
            }
        });

        levelUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                List<Monster> monsters = player.getMonsters();
                String[] monsterNames = new String[monsters.size()];
                for (int i = 0; i < monsters.size(); i++) {
                    monsterNames[i] = monsters.get(i).getName();
                }

                String chosenMonsterName = (String) JOptionPane.showInputDialog(homebaseFrame,
                        "Pilih monster yang akan dinaikkan levelnya:",
                        "Pilih Monster",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        monsterNames,
                        monsterNames[0]);

                if (chosenMonsterName != null) {
                    Monster chosenMonster = null;
                    for (Monster monster : monsters) {
                        if (monster.getName().equals(chosenMonsterName)) {
                            chosenMonster = monster;
                            break;
                        }
                    }

                    if (chosenMonster != null) {
                        boolean levelUpSuccess = player.levelUpMonster(chosenMonster);
                        if (levelUpSuccess) {
                            String log = chosenMonster.getName() + " berhasil naik level!";
                            logAction(log);
                            JOptionPane.showMessageDialog(homebaseFrame, log);
                        } else {
                            String log = chosenMonster.getName() + " tidak memiliki EP yang cukup untuk naik level!";
                            logAction(log);
                            JOptionPane.showMessageDialog(homebaseFrame, log);
                        }
                    }
                }
            }
        });

        pergiKeDungeonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                List<Monster> monsters = player.getMonsters();
                String[] monsterNames = new String[monsters.size()];
                for (int i = 0; i < monsters.size(); i++) {
                    monsterNames[i] = monsters.get(i).getName();
                }

                List<Monster> chosenMonsters = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    String chosenMonsterName = (String) JOptionPane.showInputDialog(homebaseFrame,
                            "Pilih monster " + (i + 1) + " untuk pergi ke dungeon:",
                            "Pilih Monster",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            monsterNames,
                            monsterNames[0]);

                    if (chosenMonsterName != null) {
                        for (Monster monster : monsters) {
                            if (monster.getName().equals(chosenMonsterName)) {
                                chosenMonsters.add(monster);
                                break;
                            }
                        }
                    }
                }

                if (chosenMonsters.size() == 3) {
                    player.setDungeonMonsters(chosenMonsters); // Set the chosen monsters to the player
                    dungeon = new Dungeon();
                    logAction("Pergi ke dungeon dengan monster: " + chosenMonsters.toString());
                    JOptionPane.showMessageDialog(homebaseFrame, "Anda pergi ke dungeon!");
                    showDungeon();
                    homebaseFrame.dispose();
                } else {
                    logAction("Gagal pergi ke dungeon. Harus memilih 3 monster.");
                    JOptionPane.showMessageDialog(homebaseFrame, "Anda harus memilih 3 monster untuk pergi ke dungeon.");
                }
            }
        });

        keluarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                int confirm = JOptionPane.showConfirmDialog(homebaseFrame, "Apakah Anda ingin keluar?", "Keluar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    logAction("Keluar dari permainan.");
                    System.exit(0);
                }
            }
        });

        homebasePanel.add(homebaseLabel, BorderLayout.NORTH);
        homebasePanel.add(buttonPanel, BorderLayout.CENTER);

        homebaseFrame.add(homebasePanel);
        homebaseFrame.setVisible(true);
    }

    private void showDungeon() {
        JFrame dungeonFrame = new JFrame("Dungeon");
        dungeonFrame.setSize(1366, 768);
        dungeonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BackgroundPane1 dungeonPanel = new BackgroundPane1(System.getProperty("user.home") + "/Downloads/pokemon Dungeon.jpg");
        dungeonPanel.setLayout(new BorderLayout());

        JLabel dungeonLabel = new JLabel("Dungeon");
        dungeonLabel.setFont(new java.awt.Font("Tahoma", 1, 30));
        dungeonLabel.setForeground(new java.awt.Color(255, 255, 255));
        dungeonLabel.setHorizontalAlignment(SwingConstants.CENTER);

        battleStatusLabel = new JLabel("");
        battleStatusLabel.setFont(new java.awt.Font("Tahoma", 1, 25));
        battleStatusLabel.setForeground(new java.awt.Color(255, 255, 255));
        battleStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        battleLogTextArea = new JTextArea();
        battleLogTextArea.setEditable(false);
        battleLogTextArea.setFont(new java.awt.Font("Tahoma", 1, 18));
        battleLogTextArea.setForeground(new java.awt.Color(255, 255, 255));
        battleLogTextArea.setBackground(new java.awt.Color(0, 0, 0));

        attackButton = new JButton("Attack");
        specialAttackButton = new JButton("Special Attack");
        elementalAttackButton = new JButton("Elemental Attack");
        defendButton = new JButton("Defend");
        useItemButton = new JButton("Use Item");
        runButton = new JButton("Run");

        Dimension buttonSize = new Dimension(180, 60); // Slightly larger button size

        attackButton.setFont(new java.awt.Font("Tahoma", 1, 25));
        attackButton.setPreferredSize(buttonSize);
        specialAttackButton.setFont(new java.awt.Font("Tahoma", 1, 25));
        specialAttackButton.setPreferredSize(buttonSize);
        elementalAttackButton.setFont(new java.awt.Font("Tahoma", 1, 25));
        elementalAttackButton.setPreferredSize(buttonSize);
        defendButton.setFont(new java.awt.Font("Tahoma", 1, 25));
        defendButton.setPreferredSize(buttonSize);
        useItemButton.setFont(new java.awt.Font("Tahoma", 1, 25));
        useItemButton.setPreferredSize(buttonSize);
        runButton.setFont(new java.awt.Font("Tahoma", 1, 25));
        runButton.setPreferredSize(buttonSize);

        battlePanel = new JPanel();
        battlePanel.setOpaque(false);
        battlePanel.setLayout(new GridBagLayout()); // Using GridBagLayout for better control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Increased padding around buttons

        // First row (3 buttons)
        gbc.gridx = 1;
        gbc.gridy = 0;
        battlePanel.add(attackButton, gbc);
        gbc.gridx = 2;
        battlePanel.add(specialAttackButton, gbc);
        gbc.gridx = 3;
        battlePanel.add(elementalAttackButton, gbc);

        // Second row (3 buttons)
        gbc.gridx = 1;
        gbc.gridy = 1;
        battlePanel.add(defendButton, gbc);
        gbc.gridx = 2;
        battlePanel.add(useItemButton, gbc);
        gbc.gridx = 3;
        battlePanel.add(runButton, gbc);

        playerPanel = new JPanel();
        playerPanel.setOpaque(false);
        playerPanel.setPreferredSize(new Dimension(200, 200));

        enemyPanel = new JPanel();
        enemyPanel.setOpaque(false);
        enemyPanel.setPreferredSize(new Dimension(200, 200));

        JPanel statusPanel = new JPanel(new GridBagLayout());
        statusPanel.setOpaque(false);
        GridBagConstraints statusGbc = new GridBagConstraints();
        statusGbc.insets = new Insets(0, 20, 0, 20); // Padding around panels
        statusGbc.fill = GridBagConstraints.BOTH;
        statusGbc.weightx = 1.5; // Equal weight for all components
        statusGbc.weighty = 1.5;

        // Player panel on the left
        statusGbc.gridx = 0;
        statusGbc.gridy = 0;
        statusPanel.add(playerPanel, statusGbc);

        // Battle status label in the center
        statusGbc.gridx = 1;
        statusPanel.add(battleStatusLabel, statusGbc);

        // Enemy panel on the right
        statusGbc.gridx = 2;
        statusPanel.add(enemyPanel, statusGbc);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.insets = new Insets(50, 50, 100, 50); // Add more padding at the sides and bottom
        centerGbc.fill = GridBagConstraints.BOTH;
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerPanel.add(statusPanel, centerGbc);

        centerGbc.gridy = 1;
        centerPanel.add(battlePanel, centerGbc);

        dungeonPanel.add(dungeonLabel, BorderLayout.NORTH);
        dungeonPanel.add(centerPanel, BorderLayout.CENTER);

        dungeonFrame.add(dungeonPanel);
        dungeonFrame.setVisible(true);

        List<Monster> wildMonsters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            wildMonsters.add(dungeon.encounterMonster());
        }

        if (!wildMonsters.isEmpty()) {
            encounterWildMonster(dungeonFrame, wildMonsters.get(0), wildMonsters.subList(1, wildMonsters.size()));
        }
    }

    private void addAttackEffect(JPanel panel) {
        final int originalX = panel.getX();
        final int originalY = panel.getY();
        final int amplitude = 10; // shake amplitude
        final int delay = 50; // milliseconds

        Timer timer = new Timer(delay, new ActionListener() {
            int count = 0;
            boolean direction = true; // true for right, false for left

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 6) { // shake 3 times
                    if (direction) {
                        panel.setLocation(originalX + amplitude, originalY);
                    } else {
                        panel.setLocation(originalX - amplitude, originalY);
                    }
                    direction = !direction;
                    count++;
                } else {
                    panel.setLocation(originalX, originalY); // reset to original position
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void encounterWildMonster(JFrame dungeonFrame, Monster wildMonster, List<Monster> remainingWildMonsters) {
        JOptionPane.showMessageDialog(dungeonFrame, "Monster anda sekarang adalah " + player.getCurrentMonster().getName() + ". Bertemu dengan " + wildMonster.getName());
        updateBattleStatus(player.getCurrentMonster(), wildMonster);

        playerPanel.removeAll();
        playerPanel.add(new MonsterPanel(player.getCurrentMonster()));
        playerPanel.revalidate();
        playerPanel.repaint();

        enemyPanel.removeAll();
        if (wildMonster != null) {
            enemyPanel.add(new MonsterPanel(wildMonster));
        }
        enemyPanel.revalidate();
        enemyPanel.repaint();

        // Clear existing action listeners
        clearActionListeners(attackButton);
        clearActionListeners(specialAttackButton);
        clearActionListeners(elementalAttackButton);
        clearActionListeners(defendButton);
        clearActionListeners(useItemButton);
        clearActionListeners(runButton);

        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                addAttackEffect(enemyPanel); // Add shaking effect on attack
                String log = player.getCurrentMonster().attack(wildMonster);
                appendBattleLog(log);
                logToFile(log);
                JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result
                checkBattleOutcome(dungeonFrame, wildMonster, remainingWildMonsters);
                addEffect(playerPanel, enemyPanel);
                if (wildMonster.getHealthPoints() > 0) {
                    wildMonsterAttacks(dungeonFrame, wildMonster);
                }
            }
        });

        specialAttackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                addAttackEffect(enemyPanel); // Add shaking effect on attack
                String log = player.getCurrentMonster().specialAttack(wildMonster);
                appendBattleLog(log);
                logToFile(log);
                JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result
                checkBattleOutcome(dungeonFrame, wildMonster, remainingWildMonsters);
                addEffect(playerPanel, enemyPanel);
                if (wildMonster.getHealthPoints() > 0) {
                    wildMonsterAttacks(dungeonFrame, wildMonster);
                }
            }
        });

        elementalAttackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                addAttackEffect(enemyPanel); // Add shaking effect on attack
                String log = player.getCurrentMonster().elementalAttack(wildMonster);
                appendBattleLog(log);
                logToFile(log);
                JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result
                checkBattleOutcome(dungeonFrame, wildMonster, remainingWildMonsters);
                addEffect(playerPanel, enemyPanel);
                if (wildMonster.getHealthPoints() > 0) {
                    wildMonsterAttacks(dungeonFrame, wildMonster);
                }
            }
        });

        defendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                String log = player.getCurrentMonster().defend();
                player.getCurrentMonster().setDefending(true); // Set defending flag
                appendBattleLog(log);
                logToFile(log);
                JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result
                // Wild monster attacks immediately after player defends
                wildMonsterAttacks(dungeonFrame, wildMonster);
            }
        });

//useItemButton.addActionListener(new ActionListener() {
//    public void actionPerformed(ActionEvent e) {
//        playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
//        List<Item> inventory = player.getInventory();
//        if (inventory.isEmpty()) {
//            String log = "No items available!";
//            appendBattleLog(log);
//            logToFile(log);
//            JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result
//        } else {
//            ItemSelectionDialog itemDialog = new ItemSelectionDialog(dungeonFrame, player, player.getCurrentMonster());
//            itemDialog.setVisible(true);
//        }
//    }
//});

useItemButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
        
        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            String log = "No items available!";
            appendBattleLog(log);
            logToFile(log);
            JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result
        } else {
            // Menampilkan dialog pemilihan item
            String[] itemNames = inventory.stream().map(Item::getType).toArray(String[]::new);
            String selectedItem = (String) JOptionPane.showInputDialog(
                dungeonFrame,
                "Pilih item yang akan digunakan:",
                "Gunakan Item",
                JOptionPane.PLAIN_MESSAGE,
                null,
                itemNames,
                itemNames[0]
            );

            if (selectedItem != null) {
                // Menggunakan item yang dipilih
                Item item = inventory.stream()
                    .filter(i -> i.getType().equals(selectedItem))
                    .findFirst()
                    .orElse(null);

                if (item != null) {
                    player.useItem(item);
                    logAction("Menggunakan " + item.getType() + " untuk " + player.getCurrentMonster().getName());
                    JOptionPane.showMessageDialog(dungeonFrame, item.getType() + " digunakan!");
                    ((MonsterPanel) playerPanel.getComponent(0)).updateHealthBar();
                } else {
                    logAction("Tidak ada " + selectedItem + " tersedia.");
                    JOptionPane.showMessageDialog(dungeonFrame, "Tidak ada " + selectedItem + " tersedia.");
                }
            }
        }
    }
});


        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound(System.getProperty("user.home") + "/Downloads/click_button.wav"); // Pastikan path ke file WAV benar
                Random random = new Random();
                String log;
                if (random.nextDouble() > 0.5) {
                    log = player.getCurrentMonster().getName() + " successfully ran away!";
                    appendBattleLog(log);
                    logToFile(log);
                    JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result
                    dungeonFrame.dispose();
                    showHomebase();
                } else {
                    log = "Failed to run away!";
                    appendBattleLog(log);
                    logToFile(log);
                    JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result
                    wildMonsterAttacks(dungeonFrame, wildMonster); // Wild monster attacks if the player fails to run
                }
            }
        });
    }

    private void wildMonsterAttacks(JFrame dungeonFrame, Monster wildMonster) {
        Random random = new Random();
        int attackType = random.nextInt(4); // 0: attack, 1: special attack, 2: elemental attack, 3: defend
        String log;
        switch (attackType) {
            case 0:
                log = wildMonster.attack(player.getCurrentMonster());
                addAttackEffect(playerPanel); // Add shaking effect on player panel
                break;
            case 1:
                log = wildMonster.specialAttack(player.getCurrentMonster());
                addAttackEffect(playerPanel); // Add shaking effect on player panel
                break;
            case 2:
                log = wildMonster.elementalAttack(player.getCurrentMonster());
                addAttackEffect(playerPanel); // Add shaking effect on player panel
                break;
            case 3:
                log = wildMonster.defend();
                wildMonster.setDefending(true); // Set defending flag
                break;
            default:
                log = "Unknown action!";
                break;
        }
        appendBattleLog(log);
        logToFile(log);
        JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result

        checkBattleOutcome(dungeonFrame, wildMonster, new ArrayList<>());
        addEffect(enemyPanel, playerPanel);
        player.getCurrentMonster().resetDefending(); // Reset defending state after attack
    }

    private void clearActionListeners(JButton button) {
        ActionListener[] listeners = button.getActionListeners();
        for (ActionListener listener : listeners) {
            button.removeActionListener(listener);
        }
    }

    private void checkBattleOutcome(JFrame dungeonFrame, Monster wildMonster, List<Monster> remainingWildMonsters) {
    if (player.getCurrentMonster().getHealthPoints() <= 0) {
        player.getCurrentMonster().setHealthPoints(0); // Ensure HP does not go below 0
        String log = player.getCurrentMonster().getName() + " dikalahkan dan dibawa kembali ke homebase!";
        appendBattleLog(log);
        logToFile(log);
        JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result

        player.removeCurrentMonster();

        if (player.getMonsters().isEmpty()) {
            String endLog = "Semua monster Anda telah dikalahkan!";
            appendBattleLog(endLog);
            logToFile(endLog);
            JOptionPane.showMessageDialog(dungeonFrame, endLog); // Show action result
            System.exit(0);
        } else {
            Monster nextMonster = player.getCurrentMonster();
            String switchLog = "Monster berikutnya adalah " + nextMonster.getName();
            appendBattleLog(switchLog);
            logToFile(switchLog);
            JOptionPane.showMessageDialog(dungeonFrame, switchLog); // Show switch result
            updateBattleStatus(nextMonster, wildMonster);

            playerPanel.removeAll();
            MonsterPanel playerMonsterPanel = new MonsterPanel(nextMonster);
            playerPanel.add(playerMonsterPanel);
            playerPanel.revalidate();
            playerPanel.repaint();
        }
    } else if (wildMonster.getHealthPoints() <= 0) {
        wildMonster.setHealthPoints(0); // Ensure HP does not go below 0
        String log = wildMonster.getName() + " dikalahkan!";
        appendBattleLog(log);
        logToFile(log);
        JOptionPane.showMessageDialog(dungeonFrame, log); // Show action result

        // Add EP to the player's current monster and update status label
        player.getCurrentMonster().addEP(50);
        String epGainLog = player.getCurrentMonster().getName() + " berhasil mendapatkan 50 EP!";
        appendBattleLog(epGainLog);
        logToFile(epGainLog);
        JOptionPane.showMessageDialog(dungeonFrame, epGainLog); // Show EP gain result

        updateBattleStatus(player.getCurrentMonster(), null); // Update battle status label
        battleStatusLabel.setText(epGainLog); // Update the label to show EP gain

        int option = JOptionPane.showOptionDialog(dungeonFrame,
                "Anda berhasil mengalahkan " + wildMonster.getName() + ". Apa yang ingin Anda lakukan selanjutnya?",
                "Battle Outcome",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Lanjutkan Battle", "Kembali ke Homebase"},
                "Lanjutkan Battle");
        if (option == JOptionPane.YES_OPTION && !remainingWildMonsters.isEmpty()) {
            encounterWildMonster(dungeonFrame, remainingWildMonsters.get(0), remainingWildMonsters.subList(1, remainingWildMonsters.size()));
        } else {
            dungeonFrame.dispose();
            showHomebase();
        }
    }

    // Update health bar
    ((MonsterPanel) playerPanel.getComponent(0)).updateHealthBar();
    if (enemyPanel.getComponentCount() > 0) {
        ((MonsterPanel) enemyPanel.getComponent(0)).updateHealthBar();
    }
}


    private void logToFile(String log) {
        try {
            battleLogWriter.write(log + "\n");
            battleLogWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirectoriesIfNotExist(Path path) {
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void logAction(String action) {
        try {
            createDirectoriesIfNotExist(logFilePath.getParent());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath.toString(), true))) {
                writer.write(action + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateBattleStatus(Monster playerMonster, Monster wildMonster) {
        if (wildMonster != null) {
            battleStatusLabel.setText("Monster anda (" + playerMonster.getName() + ") sedang melawan (" + wildMonster.getName() + ")");
        } else {
            battleStatusLabel.setText("Monster anda (" + playerMonster.getName() + ") tidak melawan apapun");
        }

        playerPanel.removeAll();
        MonsterPanel playerMonsterPanel = new MonsterPanel(playerMonster);
        playerPanel.add(playerMonsterPanel);
        String playerElement = (playerMonster.getElement() != null) ? playerMonster.getElement().toString() : "Unknown";
        JLabel playerMonsterLabel = new JLabel(playerMonster.getName() + " (" + playerElement + ") - HP: " + playerMonster.getHealthPoints());
        playerMonsterLabel.setForeground(Color.WHITE); // Mengatur warna teks menjadi putih
        playerPanel.add(playerMonsterLabel);
        playerPanel.revalidate();
        playerPanel.repaint();

        enemyPanel.removeAll();
        if (wildMonster != null) {
            MonsterPanel wildMonsterPanel = new MonsterPanel(wildMonster);
            enemyPanel.add(wildMonsterPanel);
            String wildElement = (wildMonster.getElement() != null) ? wildMonster.getElement().toString() : "Unknown";
            JLabel wildMonsterLabel = new JLabel(wildMonster.getName() + " (" + wildElement + ") - HP: " + wildMonster.getHealthPoints());
            wildMonsterLabel.setForeground(Color.WHITE); // Mengatur warna teks menjadi putih
            enemyPanel.add(wildMonsterLabel);
        }
        enemyPanel.revalidate();
        enemyPanel.repaint();
    }

    private void appendBattleLog(String message) {
        battleLogTextArea.append(message + "\n");
    }

    private void addEffect(JPanel attackerPanel, JPanel defenderPanel) {
        Timer timer = new Timer(50, new ActionListener() {
            int count = 0;
            boolean toggle = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggle) {
                    attackerPanel.setBackground(Color.YELLOW);
                    defenderPanel.setBackground(Color.ORANGE);
                } else {
                    attackerPanel.setBackground(Color.RED);
                    defenderPanel.setBackground(Color.BLUE);
                }
                toggle = !toggle;
                if (count++ > 10) {
                    ((Timer) e.getSource()).stop();
                    attackerPanel.setBackground(null);
                    defenderPanel.setBackground(null);
                }
            }
        });
        timer.start();
    }

    private void actionHelpGameActionPerformed(java.awt.event.ActionEvent evt) {
        String bantuan = "Selamat datang di GAME POKEMON UHUYY!\n\n"
            + "Petunjuk Permainan:\n"
            + "1. Tekan tombol 'PLAY' untuk memulai permainan.\n"
            + "2. Setelah permainan dimulai, Anda akan memasuki dunia Pokemon.\n"
            + "3. Pilih Pokemon Anda dan mulai petualangan Anda dengan melawan berbagai musuh.\n"
            + "4. Kalahkan musuh untuk mendapatkan pengalaman dan menaikkan level Pokemon Anda.\n"
            + "5. Gunakan item yang Anda temukan untuk memperkuat Pokemon Anda.\n"
            + "6. Anda dapat menyimpan permainan Anda kapan saja dan melanjutkannya nanti.\n"
            + "7. Jika Anda ingin keluar dari permainan, tekan tombol 'EXIT'.\n\n"
            + "Tips:\n"
            + "- Pelajari kelemahan dan kekuatan dari setiap jenis Pokemon untuk memenangkan pertempuran.\n"
            + "- Kumpulkan item langka untuk mendapatkan keuntungan dalam pertempuran.\n"
            + "- Simpan permainan Anda secara teratur untuk menghindari kehilangan progres.\n\n"
            + "Selamat bermain dan semoga berhasil!";
    
        logAction("Menampilkan bantuan permainan.");
        JOptionPane.showMessageDialog(this, bantuan, "Bantuan Permainan", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actionExitGameActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame frame = new JFrame("EXIT");
        
        if(JOptionPane.showConfirmDialog(frame, "Do you want to exit?", "GAME POKEMON", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            logAction("Keluar dari permainan.");
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUIGame frame = new GUIGame();
                frame.setSize(1366, 768);
                frame.setVisible(true);
            }
        });
    }
}
