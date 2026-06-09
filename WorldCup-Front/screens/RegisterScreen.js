import React, { useState, useContext } from 'react';
import {
  StyleSheet,
  View,
  Text,
  TextInput,
  TouchableOpacity,
  ScrollView,
  Alert,
  Dimensions,
  Platform,
} from 'react-native';
import { AuthContext } from '../context/AuthContext';
import WorldCupLogo from '../components/WorldCupLogo';
import StadiumBackground from '../components/StadiumBackground';

const windowWidth = Dimensions.get('window').width;
const isWeb = Platform.OS === 'web';
const isMobile = windowWidth < 768;

export default function RegisterScreen({ navigation }) {
  const { register } = useContext(AuthContext);
  const [formData, setFormData] = useState({
    nombre: '',
    apellidos: '',
    email: '',
    username: '',
    password: '',
    confirmPassword: '',
  });
  const [loading, setLoading] = useState(false);

  const handleChange = (field, value) => {
    setFormData(prev => ({
      ...prev,
      [field]: value,
    }));
  };

  const validateForm = () => {
    if (!formData.nombre.trim()) {
      Alert.alert('Error', 'Por favor ingresa tu nombre');
      return false;
    }
    if (!formData.apellidos.trim()) {
      Alert.alert('Error', 'Por favor ingresa tus apellidos');
      return false;
    }
    if (!formData.email.trim() || !formData.email.includes('@')) {
      Alert.alert('Error', 'Por favor ingresa un email válido');
      return false;
    }
    if (!formData.username.trim()) {
      Alert.alert('Error', 'Por favor ingresa un nombre de usuario');
      return false;
    }
    if (formData.password.length < 6) {
      Alert.alert('Error', 'La contraseña debe tener al menos 6 caracteres');
      return false;
    }
    if (formData.password !== formData.confirmPassword) {
      Alert.alert('Error', 'Las contraseñas no coinciden');
      return false;
    }
    return true;
  };

  const handleRegister = async () => {
    if (!validateForm()) return;

    setLoading(true);
    const result = await register({
      nombre: formData.nombre,
      apellidos: formData.apellidos,
      email: formData.email,
      username: formData.username,
      password: formData.password,
    });

    if (result.success) {
      Alert.alert('Éxito', 'Registro completado', [
        {
          text: 'OK',
          onPress: () => navigation.replace('Login'),
        },
      ]);
    } else {
      Alert.alert('Error', result.error);
    }
    setLoading(false);
  };

  return (
    <View style={styles.outerContainer}>
      <StadiumBackground />
      <ScrollView 
        style={[styles.container, { flex: 1 }]} 
        contentContainerStyle={styles.scrollContent}
        showsVerticalScrollIndicator={false}
      >
        {isWeb && <View style={styles.spacer} />}
        
        <View style={styles.headerSection}>
          {isWeb && <WorldCupLogo size="medium" />}
          <Text style={styles.title}>MUNDIAL 2026</Text>
          <Text style={styles.subtitle}>Crear Cuenta</Text>
          <View style={styles.decorativeLine} />
        </View>

        <View style={styles.formSection}>
        <TextInput
          style={styles.input}
          placeholder="Nombre"
          placeholderTextColor="#999"
          value={formData.nombre}
          onChangeText={(value) => handleChange('nombre', value)}
          editable={!loading}
        />

        <TextInput
          style={styles.input}
          placeholder="Apellidos"
          placeholderTextColor="#999"
          value={formData.apellidos}
          onChangeText={(value) => handleChange('apellidos', value)}
          editable={!loading}
        />

        <TextInput
          style={styles.input}
          placeholder="Email"
          placeholderTextColor="#999"
          keyboardType="email-address"
          value={formData.email}
          onChangeText={(value) => handleChange('email', value)}
          editable={!loading}
        />

        <TextInput
          style={styles.input}
          placeholder="Nombre de Usuario"
          placeholderTextColor="#999"
          value={formData.username}
          onChangeText={(value) => handleChange('username', value)}
          editable={!loading}
        />

        <TextInput
          style={styles.input}
          placeholder="Contraseña"
          placeholderTextColor="#999"
          secureTextEntry
          value={formData.password}
          onChangeText={(value) => handleChange('password', value)}
          editable={!loading}
        />

        <TextInput
          style={styles.input}
          placeholder="Confirmar Contraseña"
          placeholderTextColor="#999"
          secureTextEntry
          value={formData.confirmPassword}
          onChangeText={(value) => handleChange('confirmPassword', value)}
          editable={!loading}
        />

        <TouchableOpacity
          style={[styles.registerButton, loading && styles.disabledButton]}
          onPress={handleRegister}
          disabled={loading}
        >
          <Text style={styles.registerButtonText}>
            {loading ? 'Registrando...' : 'Registrarse'}
          </Text>
        </TouchableOpacity>

        <View style={styles.loginSection}>
          <Text style={styles.loginText}>¿Ya tienes cuenta? </Text>
          <TouchableOpacity onPress={() => navigation.navigate('Login')}>
            <Text style={styles.loginLink}>Inicia Sesión</Text>
          </TouchableOpacity>
        </View>
      </View>
      </ScrollView>
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
  scrollContent: {
    justifyContent: isWeb ? 'center' : 'flex-start',
    flexGrow: 1,
    zIndex: 1,
  },
  spacer: {
    height: 20,
  },
  headerSection: {
    paddingHorizontal: isWeb ? 60 : 20,
    paddingTop: isWeb ? 20 : 50,
    paddingBottom: isWeb ? 40 : 30,
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
    paddingBottom: isWeb ? 60 : 40,
    paddingTop: isWeb ? 0 : 0,
    maxWidth: 600,
    alignSelf: 'center',
    width: '100%',
    ...(isWeb && {
      backgroundColor: 'rgba(26, 95, 74, 0.95)',
      borderRadius: 20,
      marginHorizontal: 20,
      marginTop: 0,
      paddingVertical: 40,
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
  registerButton: {
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
  registerButtonText: {
    color: '#0B3D2C',
    fontSize: isWeb ? 18 : 16,
    fontWeight: 'bold',
    letterSpacing: 0.5,
  },
  loginSection: {
    flexDirection: 'row',
    justifyContent: 'center',
    marginTop: 20,
  },
  loginText: {
    color: '#FFFFFF',
    fontSize: isWeb ? 16 : 14,
  },
  loginLink: {
    color: '#FFD700',
    fontSize: isWeb ? 16 : 14,
    fontWeight: 'bold',
    textDecorationLine: 'underline',
  },
});
