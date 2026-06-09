import React from 'react';
import { View, Text, StyleSheet, Platform } from 'react-native';

const isWeb = Platform.OS === 'web';

export default function WorldCupLogo({ size = 'medium' }) {
  const sizes = {
    small: {
      container: 60,
      trophy: 40,
      text: 12,
    },
    medium: {
      container: 120,
      trophy: 80,
      text: 16,
    },
    large: {
      container: 180,
      trophy: 120,
      text: 24,
    },
  };

  const currentSize = sizes[size];

  if (isWeb) {
    return (
      <View style={[styles.container, { width: currentSize.container, height: currentSize.container }]}>
        <Text style={[styles.trophy, { fontSize: currentSize.trophy }]}>🏆</Text>
        <Text style={[styles.year, { fontSize: currentSize.text }]}>2026</Text>
      </View>
    );
  }

  return (
    <View style={styles.mobileContainer}>
      <Text style={styles.mobileTrophy}>🏆</Text>
      <Text style={styles.mobileYear}>2026</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#FFD700',
    borderRadius: 20,
    marginBottom: 20,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 8 },
    shadowOpacity: 0.4,
    shadowRadius: 8,
    elevation: 10,
  },
  trophy: {
    color: '#0B3D2C',
    fontWeight: 'bold',
  },
  year: {
    color: '#0B3D2C',
    fontWeight: 'bold',
    marginTop: -5,
  },
  mobileContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 15,
  },
  mobileTrophy: {
    fontSize: 60,
  },
  mobileYear: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#FFD700',
    marginTop: -5,
  },
});
