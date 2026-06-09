import React, { useState, useContext } from 'react';
import {
  StyleSheet,
  View,
  Text,
  TextInput,
  TouchableOpacity,
  Alert,
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

export default function LoginScreen({ navigation }) {
  const { login } = useContext(AuthContext);
  const [emailOrUsername, setEmailOrUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);

  const validateForm = () => {
    if (!emailOrUsername.trim()) {
      Alert.alert('Error', 'Por favor ingresa tu email o usuario');
      return false;
    }
    if (!password.trim()) {
      Alert.alert('Error', 'Por favor ingresa tu contraseña');
      return false;
    }
    return true;
  };

  const handleLogin = async () => {
    if (!validateForm()) return;

    setLoading(true);
    const result = await login(emailOrUsername, password);

    if (result.success) {
      navigation.replace('Dashboard');
    } else {
      Alert.alert('Error', result.error || 'Error al iniciar sesión');
    }
    setLoading(false);
  };

  return (
    <View style={styles.outerContainer}>
      <StadiumBackground />
      <View style={styles.container}>
        <ScrollView 
          style={styles.scrollView}
          contentContainerStyle={styles.scrollContent}
          showsVerticalScrollIndicator={false}
        >
          {isWeb && <View style={styles.spacer} />}
          
          <View style={styles.headerSection}>
            {isWeb && <WorldCupLogo size="medium" />}
            <Text style={styles.title}>MUNDIAL 2026</Text>
            <Text style={styles.subtitle}>Iniciar Sesión</Text>
            <View style={styles.decorativeLine} />
          </View>

          <View style={styles.formSection}>
        <TextInput
          style={styles.input}
          placeholder="Email o Usuario"
          placeholderTextColor="#999"
          value={emailOrUsername}
          onChangeText={setEmailOrUsername}
          editable={!loading}
        />

        <TextInput
          style={styles.input}
          placeholder="Contraseña"
          placeholderTextColor="#999"
          secureTextEntry
          value={password}
          onChangeText={setPassword}
          editable={!loading}
        />

        <TouchableOpacity
          style={[styles.loginButton, loading && styles.disabledButton]}
          onPress={handleLogin}
          disabled={loading}
        >
          <Text style={styles.loginButtonText}>
            {loading ? 'Iniciando...' : 'Iniciar Sesión'}
          </Text>
        </TouchableOpacity>

        <View style={styles.registerSection}>
          <Text style={styles.registerText}>¿No tienes cuenta? </Text>
          <TouchableOpacity onPress={() => navigation.navigate('Register')}>
            <Text style={styles.registerLink}>Registrate</Text>
          </TouchableOpacity>
        </View>
      </View>
      </ScrollView>

      <View style={styles.footer}>
        <Text style={styles.footerText}>Simula los resultados del Mundial 2026</Text>
      </View>
    </View>
  </View>
  );
}

const styles = StyleSheet.create({
  outerContainer: {
    flex: 1,
    backgroundColor: '#0B3D2C',
  },
  container: {
    flex: 1,
    backgroundColor: isWeb ? 'transparent' : '#0B3D2C',
  },
  scrollView: {
    flex: 1,
  },
  scrollContent: {
    flexGrow: 1,
    justifyContent: isWeb ? 'center' : 'flex-start',
    zIndex: 1,
  },
  spacer: {
    height: 20,
  },
  headerSection: {
    paddingHorizontal: isWeb ? 60 : 20,
    paddingTop: isWeb ? 20 : 80,
    paddingBottom: isWeb ? 40 : 0,
    alignItems: 'center',
    maxWidth: 600,
    alignSelf: 'center',
    width: '100%',
    ...(isWeb && {
      backgroundColor: 'rgba(26, 95, 74, 0.95)',
      borderRadius: 20,
      marginHorizontal: 20,
      paddingVertical: 40,
    }),
  },
  title: {
    fontSize: isWeb ? 36 : 28,
    fontWeight: 'bold',
    color: '#FFD700',
    letterSpacing: 2,
  },
  subtitle: {
    fontSize: isWeb ? 28 : 24,
    fontWeight: '600',
    color: '#FFFFFF',
    marginTop: 10,
  },
  decorativeLine: {
    width: 60,
    height: 3,
    backgroundColor: '#FFD700',
    marginTop: 15,
    borderRadius: 2,
  },
  formSection: {
    paddingHorizontal: isWeb ? 60 : 20,
    paddingVertical: isWeb ? 40 : 40,
    maxWidth: 600,
    alignSelf: 'center',
    width: '100%',
    ...(isWeb && {
      backgroundColor: 'rgba(26, 95, 74, 0.95)',
      borderRadius: 20,
      marginHorizontal: 20,
      marginTop: 0,
    }),
  },
  input: {
    backgroundColor: '#FFFFFF',
    borderRadius: 10,
    paddingHorizontal: 15,
    paddingVertical: isWeb ? 14 : 12,
    marginBottom: 15,
    fontSize: isWeb ? 16 : 16,
    color: '#333',
    borderWidth: 2,
    borderColor: '#E0E0E0',
    ...(isWeb && {
      outlineWidth: 0,
    }),
  },
  loginButton: {
    backgroundColor: '#FFD700',
    paddingVertical: isWeb ? 16 : 14,
    borderRadius: 10,
    alignItems: 'center',
    marginTop: 20,
    ...(Platform.OS !== 'web' && {
      shadowColor: '#000',
      shadowOffset: { width: 0, height: 4 },
      shadowOpacity: 0.3,
      shadowRadius: 4,
    }),
    elevation: Platform.OS === 'android' ? 5 : 0,
  },
  disabledButton: {
    opacity: 0.6,
  },
  loginButtonText: {
    color: '#0B3D2C',
    fontSize: isWeb ? 18 : 16,
    fontWeight: 'bold',
  },
  registerSection: {
    flexDirection: 'row',
    justifyContent: 'center',
    marginTop: 20,
  },
  registerText: {
    color: '#FFFFFF',
    fontSize: isWeb ? 16 : 14,
  },
  registerLink: {
    color: '#FFD700',
    fontSize: isWeb ? 16 : 14,
    fontWeight: 'bold',
    textDecorationLine: 'underline',
  },
  footer: {
    paddingBottom: isWeb ? 0 : 30,
    alignItems: 'center',
    display: isWeb ? 'none' : 'flex',
  },
  footerText: {
    color: '#FFD700',
    fontSize: 12,
    fontStyle: 'italic',
  },
});
