import React, { useContext } from 'react';
import {
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
  Alert,
  SafeAreaView,
  Dimensions,
  Platform,
  ScrollView,
} from 'react-native';
import { AuthContext } from '../context/AuthContext';
import WorldCupLogo from '../components/WorldCupLogo';
import StadiumBackground from '../components/StadiumBackground';

const windowWidth = Dimensions.get('window').width;
const isWeb = Platform.OS === 'web';
const isMobile = windowWidth < 768;

export default function DashboardScreen({ navigation }) {
  const { user, logout } = useContext(AuthContext);

  const handleLogout = () => {
    Alert.alert(
      'Cerrar Sesión',
      '¿Estás seguro de que deseas cerrar sesión?',
      [
        { text: 'Cancelar', style: 'cancel' },
        {
          text: 'Sí, cerrar sesión',
          onPress: () => {
            logout();
            navigation.replace('Login');
          },
          style: 'destructive',
        },
      ]
    );
  };

  return (
    <SafeAreaView style={styles.container}>
      <StadiumBackground />
      <ScrollView 
        style={styles.scrollView}
        contentContainerStyle={styles.scrollContent}
        showsVerticalScrollIndicator={false}
      >
        <View style={styles.headerSection}>
          {isWeb && <WorldCupLogo size="medium" />}
          <Text style={styles.title}>MUNDIAL 2026</Text>
          <View style={styles.decorativeLine} />
        </View>

        <View style={styles.contentSection}>
          <View style={styles.welcomeCard}>
            <View style={styles.welcomeContent}>
              <Text style={styles.welcomeTitle}>¡Bienvenido!</Text>
              <Text style={styles.welcomeSubtitle}>
                {user?.nombre || 'Usuario'}
              </Text>
              <Text style={styles.welcomeText}>
                Simula los resultados de los partidos del Mundial 2026
              </Text>
            </View>
          </View>

          <View style={styles.featuresGrid}>
            <View style={styles.featureCard}>
              <Text style={styles.featureIcon}>⚽</Text>
              <Text style={styles.featureTitle}>Predicciones</Text>
              <Text style={styles.featureDescription}>
                Predice los resultados de los partidos
              </Text>
            </View>

            <View style={styles.featureCard}>
              <Text style={styles.featureIcon}>🏆</Text>
              <Text style={styles.featureTitle}>Estadísticas</Text>
              <Text style={styles.featureDescription}>
                Consulta estadísticas en tiempo real
              </Text>
            </View>

            <View style={styles.featureCard}>
              <Text style={styles.featureIcon}>🌍</Text>
              <Text style={styles.featureTitle}>Clasificaciones</Text>
              <Text style={styles.featureDescription}>
                Sigue la tabla de posiciones
              </Text>
            </View>
          </View>
        </View>
      </ScrollView>

      <View style={styles.footerSection}>
        <TouchableOpacity
          style={styles.logoutButton}
          onPress={handleLogout}
        >
          <Text style={styles.logoutButtonText}>Cerrar Sesión</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0B3D2C',
  },
  scrollView: {
    flex: 1,
  },
  scrollContent: {
    flexGrow: 1,
    zIndex: 1,
  },
  headerSection: {
    paddingHorizontal: isWeb ? 60 : 20,
    paddingTop: isWeb ? 30 : 20,
    paddingBottom: isWeb ? 40 : 30,
    alignItems: 'center',
    maxWidth: isWeb ? 1200 : '100%',
    alignSelf: 'center',
    width: '100%',
  },
  title: {
    fontSize: isWeb ? 36 : 28,
    fontWeight: 'bold',
    color: '#FFD700',
    letterSpacing: 2,
  },
  decorativeLine: {
    width: 60,
    height: 3,
    backgroundColor: '#FFD700',
    marginTop: 15,
    borderRadius: 2,
  },
  contentSection: {
    paddingHorizontal: isWeb ? 60 : 20,
    paddingBottom: isWeb ? 40 : 30,
    maxWidth: isWeb ? 1200 : '100%',
    alignSelf: 'center',
    width: '100%',
  },
  welcomeCard: {
    backgroundColor: 'rgba(26, 95, 74, 0.9)',
    borderRadius: 15,
    paddingVertical: isWeb ? 35 : 25,
    paddingHorizontal: isWeb ? 30 : 20,
    marginBottom: isWeb ? 50 : 30,
    borderLeftWidth: 5,
    borderLeftColor: '#FFD700',
  },
  welcomeContent: {
    alignItems: 'center',
  },
  welcomeTitle: {
    fontSize: isWeb ? 28 : 24,
    fontWeight: 'bold',
    color: '#FFD700',
    marginBottom: 5,
  },
  welcomeSubtitle: {
    fontSize: isWeb ? 22 : 18,
    fontWeight: '600',
    color: '#FFFFFF',
    marginBottom: 10,
  },
  welcomeText: {
    fontSize: isWeb ? 16 : 14,
    color: '#E0E0E0',
    fontStyle: 'italic',
  },
  featuresGrid: {
    flexDirection: isWeb && !isMobile ? 'row' : 'column',
    justifyContent: isWeb && !isMobile ? 'space-between' : 'flex-start',
    gap: isWeb && !isMobile ? 20 : 0,
  },
  featureCard: {
    backgroundColor: 'rgba(26, 95, 74, 0.9)',
    borderRadius: 12,
    paddingVertical: isWeb ? 30 : 20,
    paddingHorizontal: isWeb ? 25 : 15,
    marginBottom: isWeb && !isMobile ? 0 : 15,
    alignItems: 'center',
    borderBottomWidth: 3,
    borderBottomColor: '#FFD700',
    flex: isWeb && !isMobile ? 1 : undefined,
    width: isWeb && !isMobile ? '30%' : '100%',
  },
  featureIcon: {
    fontSize: isWeb ? 50 : 40,
    marginBottom: 10,
  },
  featureTitle: {
    fontSize: isWeb ? 18 : 16,
    fontWeight: 'bold',
    color: '#FFD700',
    marginBottom: 5,
  },
  featureDescription: {
    fontSize: isWeb ? 15 : 13,
    color: '#E0E0E0',
    textAlign: 'center',
  },
  footerSection: {
    paddingHorizontal: isWeb ? 60 : 20,
    paddingVertical: isWeb ? 30 : 20,
    maxWidth: isWeb ? 1200 : '100%',
    alignSelf: 'center',
    width: '100%',
  },
  logoutButton: {
    backgroundColor: '#DC2626',
    paddingVertical: isWeb ? 16 : 14,
    borderRadius: 10,
    alignItems: 'center',
    ...(Platform.OS !== 'web' && {
      shadowColor: '#000',
      shadowOffset: { width: 0, height: 4 },
      shadowOpacity: 0.3,
      shadowRadius: 4,
    }),
    elevation: Platform.OS === 'android' ? 5 : 0,
    maxWidth: isWeb ? 400 : '100%',
    alignSelf: isWeb ? 'center' : 'stretch',
  },
  logoutButtonText: {
    color: '#FFFFFF',
    fontSize: isWeb ? 18 : 16,
    fontWeight: 'bold',
  },
});
